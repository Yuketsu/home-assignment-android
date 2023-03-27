package com.example.myapplication.data.api

import retrofit2.Response
import retrofit2.http.GET

interface ComicsAPI {
    @GET("/v1/public/comics")
    suspend fun getComics(): Response<ComicsAPIResponse>
}