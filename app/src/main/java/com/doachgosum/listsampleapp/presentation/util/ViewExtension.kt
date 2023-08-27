package com.doachgosum.listsampleapp.presentation.util

import android.view.View

fun View.setOnThrottleClickListener(interval: Long, action: (v: View) -> Unit) {
    val listener = View.OnClickListener { action(it) }
    setOnClickListener(OnThrottleClickListener(interval, listener))
}

fun View.setOnDebounceClickListener(interval: Long, action: (v: View) -> Unit) {
    val listener = View.OnClickListener { action(it) }
    setOnClickListener(OnDebounceOnClickListener(interval, listener))
}

private class OnThrottleClickListener(
    private val interval: Long,
    private val clickListener: View.OnClickListener
) : View.OnClickListener {

    private var clickable = true

    override fun onClick(v: View?) {
        if (clickable) {
            clickable = false
            v?.run {
                postDelayed({
                    clickable = true
                }, interval)
                clickListener.onClick(v)
            }
        }
    }
}

private class OnDebounceOnClickListener(
    private val interval: Long,
    private val clickListener: View.OnClickListener
): View.OnClickListener {
    private var lastClickTime = 0L

    override fun onClick(v: View) {
        val time = System.currentTimeMillis()
        if (time - lastClickTime >= interval) {
            lastClickTime = time
            clickListener.onClick(v)
        }
    }
}