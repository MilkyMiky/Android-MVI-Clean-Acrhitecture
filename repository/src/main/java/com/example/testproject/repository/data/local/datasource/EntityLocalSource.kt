package com.example.testproject.repository.data.local.datasource

import com.example.testproject.repository.data.local.db.EntityDao
import com.example.testproject.repository.data.local.entity.EntityL
import com.example.testproject.repository.domain.datasource.EntityDataSource
import com.example.testproject.repository.domain.entity.Entity
import io.reactivex.Observable

class EntityLocalSource(private val entityDao: EntityDao) : EntityDataSource {

  override fun observeEntities(): Observable<List<Entity>> =
    entityDao.getAll()
      .map { if (it.isEmpty()) emptyList() else it.map { item -> item.toDomain() } }
      .toObservable()

  private fun EntityL.toDomain() =
    Entity(
      id = id,
      data = data
    )

  private fun Entity.toLocal() =
    EntityL(
      id = id,
      data = data
    )
}