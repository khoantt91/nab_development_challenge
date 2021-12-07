@file:Suppress("unused")

package com.example.nabchallenge.utils

import android.text.TextUtils
import android.util.Patterns
import android.view.View
import com.example.nabchallenge.utils.debounce.DebouncedOnClickListener

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

fun String.isValidEmail(): Boolean = !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches();
