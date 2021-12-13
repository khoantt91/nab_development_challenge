@file:Suppress("unused")

package com.example.nabchallenge.utils

import android.text.TextUtils
import android.text.format.DateUtils
import android.util.Patterns
import android.view.View
import com.example.nabchallenge.utils.debounce.DebouncedOnClickListener
import okhttp3.internal.format
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

// View Extension
fun View.onDebounceClick(callback: (view: View) -> Unit) = this.setOnClickListener(DebouncedOnClickListener { callback.invoke(it) })

// List Extension
fun <T> List<T>.toArrayList(): ArrayList<T> = ArrayList<T>().apply { addAll(this@toArrayList) }

fun <T> ArrayList<T>.addMore(vararg items: T): ArrayList<T> {
    this.addAll(items)
    return this
}

fun <T> Iterable<T>.allNotNull(): Boolean = this.all { it != null }

fun <T> List<T>.nullIfEmpty(): List<T>? = if (this.isEmpty()) null else this

// Date Format Extension
fun Long.formatDateString(): String = SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault()).format(this)

// Number Format Extension
fun Long.round(decimal: Int): Long = "%.${decimal}f".format(this).toLong()

// Common Extension
inline fun <A, B> ifNotNull(first: A?, second: B?, block: (A, B) -> Unit): Boolean? = when (null) {
    first, second -> null
    else -> {
        block(first, second)
        true
    }
}

inline fun <A, B, C> ifNotNull(first: A?, second: B?, third: C?, block: (A, B, C) -> Unit): Boolean? = when (null) {
    first, second, third -> null
    else -> {
        block(first, second, third)
        true
    }
}

fun <T : Any> isNull(vararg data: T?): Boolean = data.all { it == null }

fun String.isValidEmail(): Boolean = !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()

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
