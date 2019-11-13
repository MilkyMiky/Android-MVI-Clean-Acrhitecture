package com.example.testproject.repository.domain.datasource

import com.example.testproject.repository.domain.entity.Entity
import io.reactivex.Observable

interface EntityDataSource {
  fun observeEntities(): Observable<List<Entity>>
}
