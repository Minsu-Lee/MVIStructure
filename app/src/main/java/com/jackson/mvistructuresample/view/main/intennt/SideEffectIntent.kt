package com.jackson.mvistructuresample.view.main.intennt

sealed class SideEffectIntent {
    data class Toast(var msg: String): SideEffectIntent()
    data class ShowPopup(
        var title: String,
        var message: String,
        val negativeTxt: String,
        val confirmTxt: String
    ): SideEffectIntent()
}