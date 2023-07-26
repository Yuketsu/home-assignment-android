package com.example.myapplication

import android.content.Context
import com.example.myapplication.data.api.ComicsAPI
import com.example.myapplication.data.db.ComicsDatabase
import com.example.myapplication.data.mappers.ComicApiResponseMapper
import com.example.myapplication.data.repositories.ComicsLocalDataSourceImpl
import com.example.myapplication.data.repositories.ComicsRemoteDataSourceImpl
import com.example.myapplication.data.repositories.ComicsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun getComicsRepository(localDataSource: ComicsLocalDataSourceImpl,
                            remoteDataSource: ComicsRemoteDataSourceImpl): ComicsRepositoryImpl {
        return ComicsRepositoryImpl(
                localDataSource,
                remoteDataSource
            )
    }

    @Provides
    @Singleton
    fun getComicsLocalDataSource(database: ComicsDatabase): ComicsLocalDataSourceImpl {
        return ComicsLocalDataSourceImpl(
            database.comicDao()
        )
    }

    @Provides
    @Singleton
    fun getComicsRemoteDataSource(comicsAPI: ComicsAPI,
                                  apiResponseMapper: ComicApiResponseMapper): ComicsRemoteDataSourceImpl {
        return ComicsRemoteDataSourceImpl(
            comicsAPI,
            apiResponseMapper
        )
    }

    @Provides
    @Singleton
    fun getDatabase(@ApplicationContext context: Context): ComicsDatabase {
        return ComicsDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun getApiResponseMapper(): ComicApiResponseMapper {
        return ComicApiResponseMapper()
    }
}