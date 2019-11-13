package com.example.testproject.repository.domain.repository

import com.example.testproject.repository.domain.entity.Entity
import io.reactivex.Observable

interface EntitiesRepository {

  fun observeEntities(): Observable<List<Entity>>

}