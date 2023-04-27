package com.example.myapplication.data.api

import com.squareup.moshi.Json

//class ComicsAPIResponse(val items: List<Comic>)

data class Comic(
    @field:Json(name = "id")
    val id : Int,
    @field:Json(name = "title")
    val title: String? = null,
    @field:Json(name = "description")
    val description: String? = null,
    @field:Json(name = "thumbnail")
    val thumbnail: Thumbnail? = null
)

data class ComicResponse(
    @field:Json(name = "copyright")
    val copyright: String,
    @field:Json(name = "data")
    val data: Data? = null,
)

data class Data(
    @field:Json(name = "limit")
    val limit: Int,
    @field:Json(name = "results")
    val results: List<Comic>? = null
)

data class Thumbnail(
    @field:Json(name = "path")
    val path: String? = null,
    @field:Json(name = "extension")
    val extension: String? = null,
)