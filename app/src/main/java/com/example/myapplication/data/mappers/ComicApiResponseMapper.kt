package com.example.myapplication.data.mappers

import com.example.myapplication.data.api.ComicsAPIResponse
import com.example.myapplication.domain.models.ComicResponseModel

class ComicApiResponseMapper {
    fun toComicsList(response: ComicsAPIResponse): List<ComicResponseModel> {
        return response.items.map {
            ComicResponseModel(
                it.id,
                it.title,
                it.description,
                it.thumbnail
            )
        }
    }
}