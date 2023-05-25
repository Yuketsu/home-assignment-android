package com.example.myapplication.data.db

import androidx.room.*
import com.example.myapplication.data.entities.ComicEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ComicDao {
    @Query("SELECT * FROM comic")
    fun getAll(): List<ComicEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(comic: ComicEntity)

    @Query("SELECT * FROM comic WHERE id = :id")
    suspend fun getById(id: Int): ComicEntity?

    @Query("DELETE FROM comic WHERE id = :id")
    suspend fun deleteById(id: Int)
}