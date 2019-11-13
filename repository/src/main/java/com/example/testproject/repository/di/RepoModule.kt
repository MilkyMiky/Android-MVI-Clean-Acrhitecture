package com.example.testproject.repository.di

import android.content.Context.MODE_PRIVATE
import com.example.testproject.repository.data.local.datasource.EntityLocalSource
import com.example.testproject.repository.data.local.db.TestProjectDbProvider
import com.example.testproject.repository.data.remote.api.TestProjectApiProvider
import com.example.testproject.repository.data.remote.datasource.EntityRemoteSource
import com.example.testproject.repository.data.repositoryImpl.EntitiesRepositoryImpl
import com.example.testproject.repository.data.serviceImpl.GlideImageLoader
import com.example.testproject.repository.domain.datasource.EntityDataSource
import com.example.testproject.repository.domain.interactors.*
import com.example.testproject.repository.domain.repository.EntitiesRepository
import com.example.testproject.repository.domain.service.ImageLoader
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val repoModule = module {

  single { TestProjectDbProvider.getInstance(androidContext()).entityDao() }

  single { TestProjectApiProvider.createApi() }

  single { androidContext().getSharedPreferences("sharedPrefs", MODE_PRIVATE) }

  single<ImageLoader> { GlideImageLoader }
  single<EntityDataSource>("local") { EntityLocalSource(get()) }
  single<EntityDataSource>("remote") { EntityRemoteSource(get()) }

  single<EntitiesRepository> { EntitiesRepositoryImpl(get("local"), get("remote")) }

  factory { ObserveEntitiesUseCase(get()) }

}