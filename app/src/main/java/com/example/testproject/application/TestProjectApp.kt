package com.example.testproject.application

import android.app.Application
import androidx.databinding.DataBindingUtil
import com.example.testproject.di.BindingComponent
import com.example.testproject.di.mainModule
import com.example.testproject.repository.di.repoModule
import org.koin.android.ext.android.startKoin

class TestProjectApp : Application() {

  override fun onCreate() {
    super.onCreate()
    startKoin(this, listOf(mainModule, repoModule))
    DataBindingUtil.setDefaultComponent(BindingComponent())
  }
}