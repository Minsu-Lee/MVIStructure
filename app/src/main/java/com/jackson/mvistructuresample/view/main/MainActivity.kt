package com.jackson.mvistructuresample.view.main

import androidx.activity.viewModels
import com.jackson.mvistructuresample.R
import com.jackson.mvistructuresample.base.BindViewModelActivity
import com.jackson.mvistructuresample.databinding.ActivityMainBinding

class MainActivity: BindViewModelActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override val viewModel: MainViewModel by viewModels()

}