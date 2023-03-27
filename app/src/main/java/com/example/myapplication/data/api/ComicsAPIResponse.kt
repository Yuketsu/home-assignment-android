package com.example.myapplication.data.api

import com.squareup.moshi.Json

class ComicsAPIResponse(val items: List<Item>)

data class Item(
    @field:Json(name = "id")
    val id : Int,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "thumbnail")
    val thumbnail: String
)