package com.example.myapplication

import com.example.myapplication.data.api.ComicsAPI
import com.example.myapplication.data.api.Constant
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun getMoshi(): Moshi {
        val moshiBuilder = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
        return moshiBuilder.build()
    }
    @Provides
    @Singleton
    fun getInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
    }
    private fun addApiKeyToRequests(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request().newBuilder()
        val originalHttpUrl = chain.request().url
        val newUrl = originalHttpUrl.newBuilder()
            .addQueryParameter("apikey", Constant.API_KEY).build()
        request.url(newUrl)
        return chain.proceed(request.build())
    }

    @Provides
    @Singleton
    fun getOkHttpClient(): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
            .addInterceptor { chain -> return@addInterceptor addApiKeyToRequests(chain) }
        return httpBuilder
            .build()
    }

    @Provides
    @Singleton
    fun getRetrofit(httpClient: OkHttpClient,
                    moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.MARVEL_APIS_ENDPOINT)
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun getComicsApi(retrofit: Retrofit): ComicsAPI {
        return retrofit.create(ComicsAPI::class.java)
    }
}