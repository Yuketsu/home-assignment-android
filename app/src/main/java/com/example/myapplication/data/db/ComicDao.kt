package com.example.myapplication.data.db

import androidx.room.*
import com.example.myapplication.data.entities.ComicEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ComicDao {
    @Query("SELECT * FROM comic")
    fun getAll(): Flow<List<ComicEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(comics: List<ComicEntity>)

    @Query("SELECT * FROM comic WHERE id = :id")
    fun getById(id: Int): ComicEntity?

    @Query("DELETE FROM comic WHERE id = :id")
    fun deleteById(id: Int)
}