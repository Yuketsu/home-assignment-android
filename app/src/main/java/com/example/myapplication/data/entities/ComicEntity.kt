package com.example.myapplication.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.data.api.Thumbnail
import com.example.myapplication.domain.models.ComicResponseModel

@Entity(tableName = "comic")
data class ComicEntity(
    @PrimaryKey
    val id: Int? = null,
    val title: String?,
    val description: String?,
    val thumbnailPath: String?,
    val thumbnailExtension: String?,
)

fun ComicEntity.toComicResponseModel(): ComicResponseModel {
    return ComicResponseModel(
        id = id!!,
        title = title,
        description = description,
        thumbnail = Thumbnail(path = thumbnailPath, extension = thumbnailExtension)
    )
}

fun ComicResponseModel.toComicRoomEntity(): ComicEntity {
    return ComicEntity(
        id = id,
        title = title,
        description = description,
        thumbnailPath = thumbnail?.path,
        thumbnailExtension = thumbnail?.extension,
    )
}