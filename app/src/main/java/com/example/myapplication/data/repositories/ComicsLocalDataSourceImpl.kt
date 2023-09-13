package com.example.myapplication.data.repositories

import com.example.myapplication.data.db.ComicDao
import com.example.myapplication.data.entities.toComicResponseModel
import com.example.myapplication.data.entities.toComicRoomEntity
import com.example.myapplication.data.interfaces.ComicsDataSource
import com.example.myapplication.domain.models.ComicResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ComicsLocalDataSourceImpl @Inject constructor(private val comicDao: ComicDao): ComicsDataSource {
    override suspend fun getAll(): Flow<List<ComicResponseModel>> {
        return comicDao.getAll()
            .map { data ->
                data.toList().map { it.toComicResponseModel()
            }
        }
    }

    override suspend fun insert(comics: List<ComicResponseModel>) {
        comicDao.insert(comics.map { it.toComicRoomEntity() })
    }

    override suspend fun getOne(id: Int): ComicResponseModel? {
        val entity = comicDao.getById(id)
        if (entity != null) {
            return entity.toComicResponseModel()
        }
        return null
    }
}