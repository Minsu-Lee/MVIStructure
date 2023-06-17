package com.jackson.mvistructuresample.intennt

sealed class SideEffectIntent {
    data class Toast(var msg: String): SideEffectIntent()
    data class ShowPopup(
        var title: String,
        var message: String,
        val confirmTxt: String
    ): SideEffectIntent()
}