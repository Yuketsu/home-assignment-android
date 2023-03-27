package com.example.myapplication.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.domain.models.ComicResponseModel

@Entity(tableName = "comic")
data class ComicEntity(
    @PrimaryKey
    val id: Int? = null,
    val title: String?,
    val description: String?,
    val thumbnail: String?
)

fun ComicEntity.toComicResponseModel(): ComicResponseModel {
    return ComicResponseModel(
        id = id!!,
        title = title,
        description = description,
        thumbnail = thumbnail
    )
}

fun ComicEntity.toComicRoomEntity(): ComicEntity {
    return ComicEntity(
        id = id,
        title = title,
        description = description,
        thumbnail = thumbnail
    )
}