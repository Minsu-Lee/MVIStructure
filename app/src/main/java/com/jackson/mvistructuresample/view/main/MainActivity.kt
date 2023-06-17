package com.jackson.mvistructuresample.view.main

import android.app.AlertDialog
import androidx.activity.viewModels
import com.jackson.mvistructuresample.R
import com.jackson.mvistructuresample.base.BindViewModelActivity
import com.jackson.mvistructuresample.base.ViewModelSideEffect
import com.jackson.mvistructuresample.databinding.ActivityMainBinding
import com.jackson.mvistructuresample.extensions.toast
import com.jackson.mvistructuresample.intennt.SideEffectIntent

private typealias SideEffect = ViewModelSideEffect<SideEffectIntent>
class MainActivity: BindViewModelActivity<ActivityMainBinding, MainViewModel, SideEffect, SideEffectIntent>(
    R.layout.activity_main
) {

    override val viewModel: MainViewModel by viewModels()

    override fun handleSideEffect(sideEffect: SideEffectIntent) {
        when (sideEffect) {
            is SideEffectIntent.Toast -> sideEffect.msg.toast(this)
            is SideEffectIntent.ShowPopup -> AlertDialog.Builder(this)
                .setTitle(sideEffect.title)
                .setMessage(sideEffect.message)
                .setNegativeButton(sideEffect.negativeTxt) { dialog, _ ->
                    dialog.dismiss()
                }.setPositiveButton(sideEffect.confirmTxt) { dialog, _ ->
                    onBackPressed()
                    dialog.dismiss()
                }.show()
        }
    }
}