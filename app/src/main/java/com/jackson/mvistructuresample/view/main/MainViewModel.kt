package com.jackson.mvistructuresample.view.main

import androidx.lifecycle.viewModelScope
import com.jackson.mvistructuresample.base.BaseViewModel
import com.jackson.mvistructuresample.data.repository.MainRepository
import com.jackson.mvistructuresample.intennt.MainIntent
import com.jackson.mvistructuresample.model.MainState
import com.jackson.mvistructuresample.intennt.SideEffectIntent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel: BaseViewModel() {

    private val repository: MainRepository by lazy { MainRepository() }

    private val events = Channel<MainIntent>()

    private val _sideEffectFlow = Channel<SideEffectIntent>()
    val sideEffectFlow = _sideEffectFlow.receiveAsFlow()

    // State Reducer
    val state: StateFlow<MainState> = events.receiveAsFlow()
        .runningFold(MainState(), ::reduceState)
        .stateIn(viewModelScope, SharingStarted.Eagerly, MainState())

    private fun reduceState(current: MainState, intent: MainIntent): MainState {
        return when (intent) {
            is MainIntent.Increment -> current.copy(count = current.count + 1)
            is MainIntent.Decrement -> current.copy(count = current.count - 1)
        }
    }

    fun increment() {
        launch {
            events.send(MainIntent.Increment)
            delay(200)
            val sideEffect = SideEffectIntent.Toast("incremennt, value: ${state.value}")
            _sideEffectFlow.send(sideEffect)
        }
    }

    fun decrement() {
        launch {
            events.send(MainIntent.Decrement)
            delay(200)
            val sideEffect = SideEffectIntent.Toast("decrement, value: ${state.value}")
            _sideEffectFlow.send(sideEffect)
        }
    }

    fun updateUserInfo() {
        launch {
            val user = repository.getUser()
            if (user == null) {
                val sideEffect = SideEffectIntent.ShowPopup(
                    "사용자 정보",
                    "사용자 정보를 조회 할 수 없습니다.",
                    "확인"
                )
                _sideEffectFlow.send(sideEffect)
            }
        }
    }
}