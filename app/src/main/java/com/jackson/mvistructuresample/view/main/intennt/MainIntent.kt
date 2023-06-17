package com.jackson.mvistructuresample.view.main.intennt

sealed interface MainIntent{
    object Increment: MainIntent
    object Decrement: MainIntent
    object CallUserInfo: MainIntent
}