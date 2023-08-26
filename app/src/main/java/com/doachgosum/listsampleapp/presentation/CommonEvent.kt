package com.doachgosum.listsampleapp.presentation

sealed class CommonEvent {
    data class ShowToast(val msg: String): CommonEvent()
}
