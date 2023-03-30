package com.example.myapplication.presentation.comics.list.mappers

import com.example.myapplication.domain.models.ComicResponseModel
import com.example.myapplication.presentation.comics.list.models.ComicsListResponseModel

class ComicsListResponseModelMapper {
    fun toComicsListResponseModel(responseModel: ComicResponseModel): ComicsListResponseModel {
        return ComicsListResponseModel(
            id = responseModel.id.toString(),
            title = responseModel.title,
            description = responseModel.description,
            thumbnail = responseModel.thumbnail
        )
    }
}