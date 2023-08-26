package com.doachgosum.listsampleapp

import android.util.Log

object Logger {

    private const val TAG_DEBUG = "tag_debug"
    private const val TAG_NETWORK = "tag_network"

    fun d(msg: String) {
        Log.d(TAG_DEBUG, msg)
    }

    fun n(msg: String) {
        Log.d(TAG_NETWORK, msg)
    }

}