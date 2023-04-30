package com.jackson.mvistructuresample.view.main

import com.jackson.mvistructuresample.base.BaseViewModel
import com.jackson.mvistructuresample.extensions.toast

class MainViewModel: BaseViewModel() {

    fun testProc() {
        "testProc".toast()
    }
}