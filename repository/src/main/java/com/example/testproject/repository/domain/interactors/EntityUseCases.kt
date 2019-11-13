package com.example.testproject.repository.domain.interactors

import com.example.testproject.repository.domain.entity.Entity
import com.example.testproject.repository.domain.repository.EntitiesRepository
import io.reactivex.Observable

class ObserveEntitiesUseCase(private val repository: EntitiesRepository) {

  fun execute(): Observable<List<Entity>> = repository.observeEntities()
}
