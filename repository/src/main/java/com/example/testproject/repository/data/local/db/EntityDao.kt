package com.example.testproject.repository.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import com.example.testproject.repository.data.local.entity.EntityL

@Dao
interface EntityDao {

  @Query("SELECT * FROM EntityL ORDER BY dbId ASC")
  fun getAll(): Flowable<List<EntityL>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(entity: EntityL): Long

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAll(entityList: List<EntityL>): List<Long>

  @Query("DELETE FROM EntityL")
  fun deleteAll(): Int

  @Query("SELECT * FROM EntityL WHERE id = :id")
  fun find(id: Int): Flowable<List<EntityL>>

  @Query("SELECT * FROM EntityL WHERE id LIKE '%' || :id || '%'")
  fun findById(id: String): Flowable<List<EntityL>>
}