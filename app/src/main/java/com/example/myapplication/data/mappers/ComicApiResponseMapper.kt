package com.example.myapplication.data.mappers

import com.example.myapplication.data.api.ComicResponse
import com.example.myapplication.domain.models.ComicResponseModel

class ComicApiResponseMapper {
    fun toComicsList(response: ComicResponse): List<ComicResponseModel> {
        return response.data?.results?.map {
            ComicResponseModel(
                it.id,
                it.title,
                it.description,
                it.thumbnail
            )
        }.orEmpty()
    }
}