package com.jackson.mvistructuresample.base

import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.flow.Flow

interface ViewModelSideEffect<SIDE_EFFECT> {
    val sideEffectFlow: Flow<SIDE_EFFECT>
    fun initSideEffect(
        lifecycleScope: LifecycleCoroutineScope,
        handleSideEffect: suspend (SIDE_EFFECT) -> Unit
    )
}