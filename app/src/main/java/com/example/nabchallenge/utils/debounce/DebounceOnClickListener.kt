package com.example.nabchallenge.utils.debounce

import android.os.SystemClock
import android.view.View
import java.util.*
import kotlin.math.abs


/**
 *  A Debounced OnClickListener
 *  Rejects clicks that are too close together in time.
 *  This class is safe to use as an OnClickListener for multiple views, and will debounce each one separately.
 */
@Suppress("unused")
class DebouncedOnClickListener(private val minimumInterval: Long = TIME_DEBOUNCE, private val onDebouncedListener: ((view: View) -> Unit)?) : View.OnClickListener {

    private var lastClickMap: WeakHashMap<View, Long> = WeakHashMap()

    override fun onClick(clickedView: View?) {
        if (clickedView == null) return
        val previousClickTimestamp = lastClickMap[clickedView]
        val currentTimestamp = SystemClock.uptimeMillis()

        lastClickMap[clickedView] = currentTimestamp
        if (previousClickTimestamp == null || abs(currentTimestamp - previousClickTimestamp) > minimumInterval) {
            onDebouncedListener?.invoke(clickedView)
        }
    }

    companion object {
        const val TIME_DEBOUNCE = 500L
    }
}