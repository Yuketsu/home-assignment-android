package com.example.myapplication.presentation.comics.list.models

import com.example.myapplication.data.api.Thumbnail

data class ComicsListResponseModel(
    val id: String,
    val title: String?,
    val description: String?,
    val thumbnail: Thumbnail?
)