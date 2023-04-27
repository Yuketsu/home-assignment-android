package com.example.myapplication.domain.models

import com.example.myapplication.data.api.Thumbnail

data class ComicResponseModel(
    val id: Int,
    val title: String?,
    val description: String?,
    val thumbnail: Thumbnail?
)