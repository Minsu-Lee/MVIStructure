package com.jackson.mvistructuresample.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel(), CoroutineScope {

    open val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.e("BaseViewModel", exception.message ?: "")
        exception.printStackTrace()
    }

    override val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext + dispatcher + exceptionHandler

}