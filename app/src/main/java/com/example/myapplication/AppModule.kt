package com.example.myapplication

import android.content.Context
import com.example.myapplication.data.api.NetworkModule
import com.example.myapplication.data.db.ComicsDatabase
import com.example.myapplication.data.interfaces.ComicsDataSource
import com.example.myapplication.data.mappers.ComicApiResponseMapper
import com.example.myapplication.data.repositories.ComicsLocalDataSourceImpl
import com.example.myapplication.data.repositories.ComicsRemoteDataSourceImpl
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
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun comicsRepository(@ApplicationContext context: Context): ComicsRepository {
        // useful because this method can be accessed by multiple threads
        synchronized(this) {
            return comicsRepository ?: createComicsRepository(context)
        }
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

    private var database: ComicsDatabase? = null
    private val networkModule by lazy {
        NetworkModule()
    }

    @Volatile
    private var comicsRepository: ComicsRepositoryImpl? = null

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

    private fun createComicsLocalDataSource(context: Context): ComicsDataSource {
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