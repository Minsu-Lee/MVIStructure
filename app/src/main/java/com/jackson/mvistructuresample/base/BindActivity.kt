package com.jackson.mvistructuresample.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle

abstract class BindActivity<B: ViewDataBinding>(
    @LayoutRes private val layoutId: Int
): AppCompatActivity() {

    private var _binding: B? = null
    protected val binding: B get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, layoutId)
        initView()
    }

    open fun initView() {}

    /**
     * Destroy 시점이 아닌 경우에만 block 을 활성화합니다.
     */
    protected fun bind(block: B.()->Unit) {
        if (lifecycle.currentState != Lifecycle.State.DESTROYED) {
            _binding?.let(block::invoke)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}