package com.example.myapplication.presentation.comics.list.models

data class ComicsListResponseModel(
    val id: String,
    val title: String?,
    val description: String?,
    val thumbnail: String?
)