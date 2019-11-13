package com.example.testproject.di

import com.example.testproject.common.ImageBindingAdapter
import com.example.testproject.ui.demo.DemoViewModel
import com.example.testproject.ui.test.TestViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

val mainModule = module {

  single { ImageBindingAdapter(get()) }

  viewModel { DemoViewModel() }

  viewModel { TestViewModel() }
}

class BindingComponent : androidx.databinding.DataBindingComponent, KoinComponent {

  private val imgLoader: ImageBindingAdapter by inject()

  override fun getImageBindingAdapter(): ImageBindingAdapter = imgLoader
}