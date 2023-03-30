package com.example.myapplication

import android.content.Context
import com.example.myapplication.di.ServiceLocator
import com.example.myapplication.domain.interfaces.ComicsRepository
import com.example.myapplication.domain.interfaces.usecases.GetAllComicsUseCase
import com.example.myapplication.domain.interfaces.usecases.GetComicUseCase
import com.example.myapplication.domain.usecases.comics.GetComics
import com.example.myapplication.domain.usecases.comics.GetOneComic
import com.example.myapplication.presentation.comics.list.mappers.ComicsListResponseModelMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private fun comicsRepository(context: Context): ComicsRepository {
        return ServiceLocator().provideComicsRepository(context)
    }

    @Provides
    fun getListResponseMapper(): ComicsListResponseModelMapper {
        return ComicsListResponseModelMapper()
    }

    @Provides
    fun getAllComicsUseCase(@ApplicationContext context: Context): GetAllComicsUseCase {
        return GetComics(comicsRepository(context))
    }

    @Provides
    fun getComicUseCase(@ApplicationContext context: Context): GetComicUseCase {
        return GetOneComic(comicsRepository(context))
    }
}