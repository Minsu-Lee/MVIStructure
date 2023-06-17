package com.jackson.mvistructuresample.intennt

sealed interface MainIntent{
    object Increment: MainIntent
    object Decrement: MainIntent
    object CallUserInfo: MainIntent
}