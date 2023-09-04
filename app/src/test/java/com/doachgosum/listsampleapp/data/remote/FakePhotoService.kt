package com.doachgosum.listsampleapp.data.remote

import com.doachgosum.listsampleapp.data.remote.dto.PhotoApiModel
import kotlinx.coroutines.delay

class FakePhotoService(
    private val photos: List<PhotoApiModel>?
): PhotoService {
    override suspend fun getPhotos(size: Int, offset: Int): List<PhotoApiModel> {
        delay(300)

        return photos ?: kotlin.run {
            throw Exception("photo list is null")
         }
    }
}