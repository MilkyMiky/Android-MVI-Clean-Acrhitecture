package com.example.testproject.repository.data.repositoryImpl

import com.example.testproject.repository.domain.datasource.EntityDataSource
import com.example.testproject.repository.domain.entity.Entity
import com.example.testproject.repository.domain.repository.EntitiesRepository
import io.reactivex.Observable

class EntitiesRepositoryImpl(
  private val localSource: EntityDataSource,
  private val remoteSource: EntityDataSource
) : EntitiesRepository {

  private val entitiesObservable: Observable<List<Entity>> = localSource.observeEntities().share()

    override fun observeEntities(): Observable<List<Entity>> = entitiesObservable

}