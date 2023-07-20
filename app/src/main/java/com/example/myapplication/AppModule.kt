package com.example.myapplication

import com.example.myapplication.data.repositories.ComicsRepositoryImpl
import com.example.myapplication.domain.interfaces.ComicsRepository
import com.example.myapplication.domain.interfaces.usecases.GetAllComicsUseCase
import com.example.myapplication.domain.interfaces.usecases.GetComicUseCase
import com.example.myapplication.domain.usecases.comics.GetComics
import com.example.myapplication.domain.usecases.comics.GetOneComic
import com.example.myapplication.presentation.comics.list.mappers.ComicsListResponseModelMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun comicsRepository(repository: ComicsRepositoryImpl): ComicsRepository {
        // useful because this method can be accessed by multiple threads
        synchronized(this) {
            return repository
        }
    }

    @Provides
    fun getListResponseMapper(): ComicsListResponseModelMapper {
        return ComicsListResponseModelMapper()
    }

    @Provides
    fun getAllComicsUseCase(comicsRepository: ComicsRepository): GetAllComicsUseCase {
        return GetComics(comicsRepository)
    }

    @Provides
    fun getComicUseCase(comicsRepository: ComicsRepository): GetComicUseCase {
        return GetOneComic(comicsRepository)
    }
}