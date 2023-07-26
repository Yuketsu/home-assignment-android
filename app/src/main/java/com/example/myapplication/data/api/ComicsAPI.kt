package com.example.myapplication.data.api

import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ComicsAPI {
    @GET("comics")
     fun getComics(
        @Query("ts") ts: String = Constant.ts,
        @Query("hash") hash: String = Constant.hash(),
        @Query("limit") limit: String = Constant.limit
    ): Observable<Response<ComicResponse>>
}