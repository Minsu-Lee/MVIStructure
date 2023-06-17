package com.jackson.mvistructuresample.base

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

abstract class BaseViewModel<STATE, SIDE_EFFECT, INTENT>(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    started: SharingStarted = SharingStarted.Eagerly
): CoroutineViewModel(dispatcher), ViewModelSideEffect<SIDE_EFFECT> {

    /**
     * Intent
     */
    private val events: Channel<INTENT> = Channel()
    private val initState: STATE by lazy { initState() }
    val state: StateFlow<STATE> = events.receiveAsFlow()
        .runningFold(initState, ::reduceState)
        .stateIn(viewModelScope, started, initState)

    abstract fun initState(): STATE
    open fun reduceState(current: STATE, intent: INTENT): STATE = initState()

    suspend fun send(intent: INTENT) {
        events.send(intent)
    }

    /**
     * SideEffect
     */
    private val _sideEffect = Channel<SIDE_EFFECT>()
    override val sideEffectFlow: Flow<SIDE_EFFECT>
        get() = _sideEffect.receiveAsFlow()

    suspend fun sideEffect(intent: SIDE_EFFECT) {
        _sideEffect.send(intent)
    }

    // 자동으로 실행되기 위해 Base 에서 호출
    override fun initSideEffect(
        lifecycleScope: LifecycleCoroutineScope,
        handleSideEffect: suspend (SIDE_EFFECT) -> Unit
    ) {
        sideEffectFlow.onEach(handleSideEffect)
            .launchIn(lifecycleScope)
    }
}