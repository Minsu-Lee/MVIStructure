package com.jackson.mvistructuresample.view

import android.app.Application

class MyApplication: Application() {

    companion object {
        private var _instance: MyApplication? = null
        val instance: MyApplication get() = _instance!!
    }

    override fun onCreate() {
        super.onCreate()
        _instance = this
    }
}