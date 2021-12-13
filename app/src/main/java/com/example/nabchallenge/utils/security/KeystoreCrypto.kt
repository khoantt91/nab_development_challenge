
package com.example.nabchallenge.utils.security

import android.content.Context
import android.os.Build
import android.security.KeyPairGeneratorSpec
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import com.example.nabchallenge.utils.log.eLog
import java.math.BigInteger
import java.nio.charset.Charset
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.PublicKey
import java.security.spec.MGF1ParameterSpec
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.OAEPParameterSpec
import javax.crypto.spec.PSource
import javax.security.auth.x500.X500Principal

/***
 * Created by Khoa Nguyen on 10/12/2021.
 * Copyright (c) 2021 Propzy. All rights reserved.
 * Email: khoantt91@gmail.com
 */

class KeystoreCrypto constructor(val context: Context) {

    @Suppress("DEPRECATION")
    private fun genKey(alias: String) {
        val generator = KeyPairGenerator.getInstance(ALGORITHM_RSA, SECURITY_PROVIDER_ANDROID_KEY_STORE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Above Android M
            val spec = KeyGenParameterSpec.Builder(alias, KeyProperties.PURPOSE_DECRYPT or KeyProperties.PURPOSE_ENCRYPT)
                .setDigests(KeyProperties.DIGEST_SHA256, KeyProperties.DIGEST_SHA512)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_OAEP)
                .build()
            generator.initialize(spec)

        } else {
            // Below Android M
            val start = Calendar.getInstance()
            val end = Calendar.getInstance()
            end.add(Calendar.YEAR, 30)
            val spec = KeyPairGeneratorSpec.Builder(context)
                .setAlias(alias)
                .setSubject(X500Principal("CN=Sample Name, O=Android Authority"))
                .setSerialNumber(BigInteger.ONE)
                .setStartDate(start.time)
                .setEndDate(end.time)
                .build()
            generator.initialize(spec)

        }
        generator.generateKeyPair()
    }

    fun encryptData(alias: String, data: String): String? {
        try {
            val keyStore = KeyStore.getInstance(SECURITY_PROVIDER_ANDROID_KEY_STORE)
            keyStore.load(null)
            var privateKeyEntry = keyStore.getEntry(alias, null) as? KeyStore.PrivateKeyEntry
            var publicKey = privateKeyEntry?.certificate?.publicKey
            if (publicKey == null) {
                genKey(alias)
                privateKeyEntry = keyStore.getEntry(alias, null) as? KeyStore.PrivateKeyEntry
                publicKey = privateKeyEntry?.certificate?.publicKey as PublicKey
            }
            val cipher = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Cipher.getInstance(TRANSFORMATION_ABOVE_M)
            } else {
                Cipher.getInstance(TRANSFORMATION_BELOW_M)
            }

            val spec = OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA1, PSource.PSpecified.DEFAULT)
            cipher.init(Cipher.ENCRYPT_MODE, publicKey, spec)
            val encryptedByte = cipher.doFinal(data.encodeToByteArray())
            return encryptedByte.toHex()
        } catch (e: Exception) {
            eLog("ENCRYPTION ERROR: $e")
            return null
        }
    }

    fun decryptData(alias: String, data: String): String? {
        try {
            val keyStore = KeyStore.getInstance(SECURITY_PROVIDER_ANDROID_KEY_STORE)
            keyStore.load(null)
            val privateKeyEntry = keyStore.getEntry(alias, null) as? KeyStore.PrivateKeyEntry
            val privateKey = privateKeyEntry?.privateKey ?: throw Exception("Alias key hasn't been generated")
            val cipher = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Cipher.getInstance(TRANSFORMATION_ABOVE_M)
            } else {
                Cipher.getInstance(TRANSFORMATION_BELOW_M)
            }

            val spec = OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA1, PSource.PSpecified.DEFAULT)
            cipher.init(Cipher.DECRYPT_MODE, privateKey, spec)
            val decryptedByte = cipher.doFinal(data.hexStringToByteArray())
            return String(decryptedByte, Charset.forName("UTF-8"))
        } catch (e: Exception) {
            eLog("DECRYPTION ERROR: $e")
            return null
        }
    }

}

// Config
const val ALGORITHM_RSA = "RSA"
const val TRANSFORMATION_ABOVE_M = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding"
const val TRANSFORMATION_BELOW_M = "RSA/ECB/PKCS1Padding"
const val SECURITY_PROVIDER_ANDROID_KEY_STORE = "AndroidKeyStore"

// Hex converter extension
fun String.hexStringToByteArray(): ByteArray {

    val b = ByteArray(this.length / 2)
    for (i in b.indices) {
        val index = i * 2
        val v = Integer.parseInt(this.substring(index, index + 2), 16)
        b[i] = v.toByte()
    }
    return b
}

private const val HEX_STRING = "0123456789ABCDEF"
private val HEX_CHARS_ARRAY = HEX_STRING.toCharArray()
fun ByteArray.toHex(): String {
    val result = StringBuffer()

    forEach {
        val octet = it.toInt()
        val firstIndex = (octet and 0xF0).ushr(4)
        val secondIndex = octet and 0x0F
        result.append(HEX_CHARS_ARRAY[firstIndex])
        result.append(HEX_CHARS_ARRAY[secondIndex])
    }


    return result.toString()
}