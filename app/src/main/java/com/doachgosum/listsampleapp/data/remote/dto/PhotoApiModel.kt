package com.doachgosum.listsampleapp.data.remote.dto

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class PhotoApiModel(
    @Json(name = "albumId") val albumId: Int,
    @Json(name = "id") val id: Int,
    @Json(name = "thumbnailUrl") val thumbnailUrl: String,
    @Json(name = "title") val title: String,
    @Json(name = "url") val url: String
)