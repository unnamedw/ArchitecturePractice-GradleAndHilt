package com.doachgosum.listsampleapp.data.remote

import com.doachgosum.listsampleapp.data.remote.dto.PhotoApiModel
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoService {

    @GET("photos")
    suspend fun getPhotos(
        @Query("_limit") size: Int,
        @Query("_start") offset: Int
    ): List<PhotoApiModel>

}