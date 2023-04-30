package com.jackson.mvistructuresample.base

import android.util.Log
import com.jackson.mvistructuresample.BuildConfig

object DLog {
    
    private val TAG: String = "DLog"
    private val isDebug: Boolean = BuildConfig.DEBUG

    private fun stackInfo(msg: String?): String {
        val stack = Throwable().stackTrace
        return try {
            val currentStack = stack[2]
            """
                ${currentStack.className}	[Line: ${currentStack.lineNumber}]	${currentStack.methodName} -> $msg
            """.trimIndent()
        } catch (e: Exception) {
            msg ?: ""
        }
    }

    fun v(msg: String?) {
        if (isDebug) {
            Log.v(TAG, stackInfo(msg))
        }
    }

    fun v(tag: String?, msg: String?) {
        if (isDebug) {
            Log.v(tag, stackInfo(msg))
        }
    }

    fun d(msg: String?) {
        if (isDebug) {
            Log.d(TAG, stackInfo(msg))
        }
    }

    fun d(tag: String?, msg: String?) {
        if (isDebug) {
            Log.d(tag, stackInfo(msg))
        }
    }

    fun e(msg: String?) {
        if (isDebug) {
            Log.e(TAG, stackInfo(msg))
        }
    }

    fun e(tag: String?, msg: String?) {
        if (isDebug) {
            Log.e(tag, stackInfo(msg))
        }
    }

    fun e(msg: String?, throwable: Throwable?) {
        if (isDebug) {
            Log.e(TAG, stackInfo(msg), throwable)
        }
    }

    fun e(tag: String?, msg: String?, throwable: Throwable?) {
        if (isDebug) {
            Log.e(tag, stackInfo(msg), throwable)
        }
    }

    fun i(msg: String?) {
        if (isDebug) {
            Log.i(TAG, stackInfo(msg))
        }
    }

    fun i(tag: String?, msg: String?) {
        if (isDebug) {
            Log.i(tag, stackInfo(msg))
        }
    }
}