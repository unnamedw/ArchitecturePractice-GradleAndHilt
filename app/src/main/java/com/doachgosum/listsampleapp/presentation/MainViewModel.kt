package com.doachgosum.listsampleapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doachgosum.listsampleapp.di.CoroutineQualifiers
import com.doachgosum.listsampleapp.domain.model.PhotoModel
import com.doachgosum.listsampleapp.domain.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val photoRepository: PhotoRepository,
    @CoroutineQualifiers.DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _commonEvent: MutableSharedFlow<CommonEvent> = MutableSharedFlow()
    val commonEvent = _commonEvent.asSharedFlow()

    private val _photoList: MutableStateFlow<List<PhotoModel>> = MutableStateFlow(emptyList())
    val photoList = _photoList.asStateFlow()

    private var fetchPhotoDataJob: Job? = null
    private var clearJob: Job? = null

    private var page: Int = 0

    fun loadMorePhotos() {
        viewModelScope.launch {
            fetchPhotoDataJob?.cancelAndJoin()

            fetchPhotoDataJob = launch {
                kotlin.runCatching {
                    _isLoading.update { true }
                    _photoList.update { oldList ->
                        val nextList = photoRepository.getPhotos(page = page)

                        oldList.plus(nextList)
                    }
                }.onSuccess {
                    page++
                }.onFailure {
                    it.printStackTrace()
                    _commonEvent.emit(CommonEvent.ShowToast(msg = "정보를 불러오는 도중 문제가 발생했어요"))
                }.also {
                    _isLoading.update { false }
                    fetchPhotoDataJob = null
                }
            }
        }
    }

    fun clear() {
        clearJob = viewModelScope.launch {

            // 기존 작업 취소
            fetchPhotoDataJob?.cancelAndJoin()
            _isLoading.update { false }

            // 목록 리셋
            _photoList.update { emptyList() }
            page = 0
        }
    }

    fun sortByTitleAsc() {
        viewModelScope.launch {
            val sortedList = withContext(defaultDispatcher) {
                _photoList.value.sortedBy { it.title }
            }
            _photoList.update { sortedList }
        }
    }

}