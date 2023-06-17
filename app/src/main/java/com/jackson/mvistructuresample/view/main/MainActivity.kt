package com.jackson.mvistructuresample.view.main

import android.app.AlertDialog
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.jackson.mvistructuresample.R
import com.jackson.mvistructuresample.base.BindViewModelActivity
import com.jackson.mvistructuresample.databinding.ActivityMainBinding
import com.jackson.mvistructuresample.extensions.toast
import com.jackson.mvistructuresample.intennt.SideEffectIntent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity: BindViewModelActivity<ActivityMainBinding, MainViewModel>(
    R.layout.activity_main
) {

    override val viewModel: MainViewModel by viewModels()

    override fun initView() {
        viewModel.sideEffectFlow
            .onEach(::handleSideEffect)
            .launchIn(lifecycleScope)
    }

    private fun handleSideEffect(sideEffect: SideEffectIntent) {
        when (sideEffect) {
            is SideEffectIntent.Toast -> sideEffect.msg.toast(this)
            is SideEffectIntent.ShowPopup -> AlertDialog.Builder(this)
                .setTitle(sideEffect.title)
                .setMessage(sideEffect.message)
                .setPositiveButton(sideEffect.confirmTxt) { dialog, _ ->
                    onBackPressed()
                    dialog.dismiss()
                }.show()
        }
    }
}