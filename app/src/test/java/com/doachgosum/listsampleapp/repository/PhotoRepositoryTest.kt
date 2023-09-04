package com.doachgosum.listsampleapp.repository

import com.doachgosum.listsampleapp.data.remote.FakePhotoService
import com.doachgosum.listsampleapp.data.remote.PhotoService
import com.doachgosum.listsampleapp.data.remote.photoList1
import com.doachgosum.listsampleapp.data.repository.PhotoRepositoryImpl
import com.doachgosum.listsampleapp.domain.repository.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test

class PhotoRepositoryTest {

    private lateinit var photoService: PhotoService
    private lateinit var photoRepository: PhotoRepository

    @Before
    fun setup() {
        photoService = FakePhotoService(photoList1)
        photoRepository = PhotoRepositoryImpl(photoService, Dispatchers.IO)
    }

    @Test
    fun `목록 확인 테스트`() = runTest {
        val result = photoRepository.getPhotos(0)

        assertThat(result.size, IsEqual(10))
    }
}