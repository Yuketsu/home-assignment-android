package com.example.myapplication

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.db.ComicsDatabase
import com.example.myapplication.data.interfaces.ComicsLocalDataSource
import com.example.myapplication.data.interfaces.ComicsRemoteDataSource
import com.example.myapplication.data.repositories.*
import com.example.myapplication.domain.interfaces.ComicsRepository
import com.example.myapplication.domain.interfaces.usecases.GetAllComicsUseCase
import com.example.myapplication.domain.usecases.comics.GetComics
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesComicsLocalDatasource(@ApplicationContext context: Context): ComicsLocalDataSource {
        val dao = ComicsDatabase.getDatabase(context).comicDao()
        return ComicsLocalDataSourceImpl(dao)
    }

    @Provides
    @Singleton
    fun providesComicsRemoteDatasource(@ApplicationContext context: Context): ComicsRemoteDataSource {
        return ComicsRemoteDataSourceImpl()
    }

    @Provides
    @Singleton
    fun providesComicsRepository(localDataSource: ComicsLocalDataSource, remoteDataSource: ComicsRemoteDataSource): ComicsRepository {
        return ComicsRepositoryImpl(comicsLocalDataSource = localDataSource, comicsRemoteDataSource = remoteDataSource)
    }

    @Provides
    @Singleton
    fun providesGetComicsUseCase(repository: ComicsRepository): GetAllComicsUseCase {
        return GetComics(comicsRepository = repository)
    }
}