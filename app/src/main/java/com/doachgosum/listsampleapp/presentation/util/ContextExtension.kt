package com.doachgosum.listsampleapp.presentation.util

import android.content.Context
import android.widget.Toast

private var toast: Toast? = null
fun Context.showToast(msg: String) {
    toast?.cancel()
    toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT)
    toast?.show()
}