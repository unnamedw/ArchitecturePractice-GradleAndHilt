package com.doachgosum.listsampleapp.repository

import com.doachgosum.listsampleapp.data.remote.photoList1
import com.doachgosum.listsampleapp.data.remote.photoList2
import com.doachgosum.listsampleapp.data.repository.mapToPhotoModelList
import com.doachgosum.listsampleapp.domain.model.PhotoModel
import com.doachgosum.listsampleapp.domain.repository.PhotoRepository

class FakePhotoRepository: PhotoRepository {

    override suspend fun getPhotos(page: Int): List<PhotoModel> {
        return when (page) {
            0 -> photoList1.mapToPhotoModelList()
            1 -> photoList2.mapToPhotoModelList()
            else -> emptyList()
        }
    }
}