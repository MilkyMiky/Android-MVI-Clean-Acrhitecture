package com.example.testproject.repository.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.testproject.repository.data.local.entity.EntityL

@Database(entities = [EntityL::class], version = 1, exportSchema = false)
@TypeConverters(DbTypeConverters::class)
abstract class TestProjectDb : RoomDatabase() {

  abstract fun entityDao(): EntityDao

}