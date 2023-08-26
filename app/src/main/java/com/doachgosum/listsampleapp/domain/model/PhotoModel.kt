package com.doachgosum.listsampleapp.domain.model

data class PhotoModel(
    val id: Int,
    val albumId: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)
