package com.jackson.mvistructuresample.extensions

import android.content.Context
import android.widget.Toast
import com.jackson.mvistructuresample.view.MyApplication

private val applicationContext: Context
    get() = MyApplication.instance

private fun makeToast(
    context: Context,
    msg: String,
    duration: Int = Toast.LENGTH_SHORT
): Toast = Toast.makeText(context, msg, duration)

fun String?.toast(context: Context): Toast? = if (!isNullOrEmpty()) {
    val toast = makeToast(context, this, Toast.LENGTH_SHORT)
    toast.show()
    toast
} else null

fun String?.toast(): Toast? = toast(MyApplication.instance)

fun toast(context: Context, msg: String): Toast? = msg.toast(context)

@JvmName("toast1")
fun toast(msg: String): Toast? = toast(applicationContext, msg)