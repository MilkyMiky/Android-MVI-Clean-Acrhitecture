package com.example.testproject.repository.data.remote.api

import com.example.testproject.repository.data.remote.entity.EntityR
import io.reactivex.Observable
import retrofit2.http.GET

interface TestProjectApi {

  @GET("path")
  fun getEntities(): Observable<List<EntityR>>
}