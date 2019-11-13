package com.example.testproject.repository.data.local.db

import android.content.Context
import androidx.room.Room

class TestProjectDbProvider {
  companion object {

    @Volatile private var INSTANCE: TestProjectDb? = null

    fun getInstance(context: Context): TestProjectDb =
      INSTANCE ?: synchronized(this) {
        INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
      }

    private fun buildDatabase(context: Context) =
      Room.databaseBuilder(context, TestProjectDb::class.java, "TestProjectDatabase")
        .build()
  }}