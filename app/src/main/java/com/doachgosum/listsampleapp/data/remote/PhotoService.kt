package com.doachgosum.listsampleapp.data.remote

import com.doachgosum.listsampleapp.data.remote.dto.PhotoApiModel
import retrofit2.http.GET

interface PhotoService {

    @GET("photos")
    suspend fun getPhotos(): List<PhotoApiModel>

}