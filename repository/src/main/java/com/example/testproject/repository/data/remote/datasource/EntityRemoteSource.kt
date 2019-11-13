package com.example.testproject.repository.data.remote.datasource

import com.example.testproject.repository.data.remote.api.TestProjectApi
import com.example.testproject.repository.domain.datasource.EntityDataSource
import com.example.testproject.repository.domain.entity.Entity
import io.reactivex.Observable

class EntityRemoteSource(private val api: TestProjectApi) : EntityDataSource {

  override fun observeEntities(): Observable<List<Entity>> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}