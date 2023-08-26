package com.doachgosum.listsampleapp.data.repository

import com.doachgosum.listsampleapp.data.remote.PhotoService
import com.doachgosum.listsampleapp.data.remote.dto.PhotoApiModel
import com.doachgosum.listsampleapp.di.CoroutineQualifiers
import com.doachgosum.listsampleapp.domain.model.PhotoModel
import com.doachgosum.listsampleapp.domain.repository.PhotoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val photoService: PhotoService,
    @CoroutineQualifiers.IoDispatcher private val ioDispatcher: CoroutineDispatcher
): PhotoRepository {

    override suspend fun getPhotos(): List<PhotoModel> = withContext(ioDispatcher) {
        photoService.getPhotos().mapToPhotoModelList()
    }
}

fun List<PhotoApiModel>.mapToPhotoModelList(): List<PhotoModel> {
    return map {
        PhotoModel(
            id = it.id,
            albumId = it.albumId,
            title = it.title,
            url = it.url,
            thumbnailUrl = it.thumbnailUrl
        )
    }
}