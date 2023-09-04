package com.doachgosum.listsampleapp.viewmodel

import com.doachgosum.listsampleapp.MainDispatcherRule
import com.doachgosum.listsampleapp.domain.repository.PhotoRepository
import com.doachgosum.listsampleapp.presentation.MainViewModel
import com.doachgosum.listsampleapp.repository.FakePhotoRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    private lateinit var fakePhotoRepository: PhotoRepository
    private lateinit var mainViewModel: MainViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        fakePhotoRepository = FakePhotoRepository()
        mainViewModel = MainViewModel(fakePhotoRepository, Dispatchers.Default)
    }

    @Test
    fun `다음 페이지를 불러오면 목록 갯수가 10개씩 증가한다`() = runTest {
        assertEquals(mainViewModel.photoList.value.size, 0)

        mainViewModel.loadMorePhotos()
        assertEquals(mainViewModel.photoList.value.size, 10)

        mainViewModel.loadMorePhotos()
        assertEquals(mainViewModel.photoList.value.size, 20)
    }

    @Test
    fun `다음 목록이 없으면 갯수가 증가하지 않는다`() = runTest {
        assertEquals(mainViewModel.photoList.value.size, 0)

        mainViewModel.loadMorePhotos()
        assertEquals(mainViewModel.photoList.value.size, 10)

        mainViewModel.loadMorePhotos()
        assertEquals(mainViewModel.photoList.value.size, 20)

        mainViewModel.loadMorePhotos()
        assertEquals(mainViewModel.photoList.value.size, 20)
    }

    @Test
    fun `리스트를 리셋하면 목록 갯수는 0이 되어야 한다`() = runTest {
        mainViewModel.loadMorePhotos()
        assertEquals(mainViewModel.photoList.value.size, 10)

        mainViewModel.clear()
        assertEquals(mainViewModel.photoList.value.size, 0)
    }

    @Test
    fun `목록이 제목순으로 정렬 되어야 한다`() = runTest {
        mainViewModel.loadMorePhotos()

        val originList = mainViewModel.photoList.value
        val sortedList = originList.sortedBy { it.title }

        mainViewModel.sortByTitleAsc()
        delay(100)
        assertEquals(mainViewModel.photoList.value, sortedList)
    }

}