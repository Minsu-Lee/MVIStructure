package com.jackson.mvistructuresample.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.jackson.mvistructuresample.BR

private typealias SideEffect<SI> = ViewModelSideEffect<SI>
abstract class BindViewModelActivity<B: ViewDataBinding, VM: ViewModel, SE: SideEffect<SI>, SI>(
    @LayoutRes private val layoutId: Int
): BindActivity<B>(layoutId) {

    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        initSideEffect()
    }

    private fun initViewModel() {
        lifecycleScope
        with(binding) {
            lifecycleOwner = this@BindViewModelActivity
            /**
             * DataBinding 은 빌드 시점에 레이아웃 파일의 내용을 분석하여 자동으로 BR 클래스를 생성
             * ( kotlin-kapt 플러그인 추가 필수 )
             */
            val isVmSetting = setVariable(BR.vm, viewModel)
            if (!isVmSetting) {
                // layout 파일 내에 vm이 정의되어 있지 않으면 NoSuchFieldException 발생합니다.
                throw NoSuchFieldException("`vm` variable not found inside ViewDataBinding layout file!!")
            }
        }
    }

    /**
     * BaseActivity 에서 SideEffect Flow의 구독행위를 진행
     */
    private fun initSideEffect() {
        (viewModel as? SE)?.initSideEffect(lifecycleScope, ::handleSideEffect)
    }

    open fun handleSideEffect(sideEffect: SI) { }
}