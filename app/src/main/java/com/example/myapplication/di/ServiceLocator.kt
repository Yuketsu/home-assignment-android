package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.BuildConfig
import com.example.myapplication.data.api.NetworkModule
import com.example.myapplication.data.db.ComicsDatabase
import com.example.myapplication.data.interfaces.ComicsLocalDataSource
import com.example.myapplication.data.mappers.ComicApiResponseMapper
import com.example.myapplication.data.repositories.ComicsLocalDataSourceImpl
import com.example.myapplication.data.repositories.ComicsRemoteDataSourceImpl
import com.example.myapplication.data.repositories.ComicsRepositoryImpl
import com.example.myapplication.domain.interfaces.ComicsRepository

class ServiceLocator {
    private var database: ComicsDatabase? = null
    private val networkModule by lazy {
        NetworkModule()
    }

    @Volatile
    var comicsRepository: ComicsRepositoryImpl? = null

    fun provideComicsRepository(context: Context): ComicsRepository {
        // useful because this method can be accessed by multiple threads
        synchronized(this) {
            return comicsRepository ?: createComicsRepository(context)
        }
    }

    private fun createComicsRepository(context: Context): ComicsRepositoryImpl {
        val newRepo =
            ComicsRepositoryImpl(
                createComicsLocalDataSource(context),
                ComicsRemoteDataSourceImpl(
                    networkModule.createComicsApi(BuildConfig.MARVEL_APIS_ENDPOINT),
                    ComicApiResponseMapper()
                )
            )
        comicsRepository = newRepo
        return newRepo
    }

    private fun createComicsLocalDataSource(context: Context): ComicsLocalDataSource {
        val database = database ?: createDataBase(context)
        return ComicsLocalDataSourceImpl(
            database.comicDao()
        )
    }

    private fun createDataBase(context: Context): ComicsDatabase {
        val result = ComicsDatabase.getDatabase(context)
        database = result
        return result
    }
}