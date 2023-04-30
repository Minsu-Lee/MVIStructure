package com.jackson.mvistructuresample.extensions

import android.content.Context
import android.widget.Toast
import com.jackson.mvistructuresample.view.MyApplication

fun String?.toast(context: Context) {
    if (!isNullOrEmpty()) {
        Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
    }
}

fun String?.toast() = toast(MyApplication.instance)