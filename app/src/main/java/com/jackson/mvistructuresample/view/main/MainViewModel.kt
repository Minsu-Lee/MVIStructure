package com.jackson.mvistructuresample.view.main

import com.jackson.mvistructuresample.base.viewModel.BaseViewModel
import com.jackson.mvistructuresample.data.repository.MainRepository
import com.jackson.mvistructuresample.view.main.intennt.MainIntent
import com.jackson.mvistructuresample.view.main.intennt.SideEffectIntent
import com.jackson.mvistructuresample.view.main.model.MainState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel: BaseViewModel<MainState, MainIntent, SideEffectIntent>() {

    private val repository: MainRepository by lazy { MainRepository() }

    override fun initState(): MainState = MainState()

    override fun reduceState(current: MainState, intent: MainIntent): MainState {
        return when (intent) {
            is MainIntent.Increment -> current.copy(count = current.count + 1)
            is MainIntent.Decrement -> current.copy(count = current.count - 1)
            is MainIntent.CallUserInfo -> {
                laodUserInfo()
                current
            }
        }
    }

    fun increment() = launch {
        send(MainIntent.Increment)
        delay(200)
        sideEffect(SideEffectIntent.Toast("incremennt, value: ${state.value}"))
    }

    fun decrement() = launch {
        send(MainIntent.Decrement)
        delay(200)
        sideEffect(SideEffectIntent.Toast("decrement, value: ${state.value}"))
    }

    fun updateUserInfo() = launch {
        send(MainIntent.CallUserInfo)
    }

    private fun laodUserInfo() = launch {
        val user = repository.getUserOrNull()
        if (user == null) {
            val sideEffect = SideEffectIntent.ShowPopup(
                "사용자 정보",
                "사용자 정보를 조회 할 수 없습니다.",
                "닫기",
                "확인"
            )
            sideEffect(sideEffect)
        }
    }
}