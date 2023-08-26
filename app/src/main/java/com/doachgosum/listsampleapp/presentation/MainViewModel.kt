package com.doachgosum.listsampleapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doachgosum.listsampleapp.domain.model.PhotoModel
import com.doachgosum.listsampleapp.domain.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val photoRepository: PhotoRepository
): ViewModel() {

    private val _commonEvent: MutableSharedFlow<CommonEvent> = MutableSharedFlow()
    val commonEvent = _commonEvent.asSharedFlow()

    private val _photoList: MutableStateFlow<List<PhotoModel>> = MutableStateFlow(emptyList())
    val photoList = _photoList.asStateFlow()

    private var fetchPhotoDataJob: Job? = null

    fun fetchPhotoData() {
        viewModelScope.launch {
            fetchPhotoDataJob?.cancelAndJoin()

            fetchPhotoDataJob = launch {
                kotlin.runCatching {
                    _photoList.value = photoRepository.getPhotos()
                }.onFailure {
                    _commonEvent.emit(CommonEvent.ShowToast(msg = "정보를 불러오는 도중 문제가 발생했어요"))
                }.also {
                    fetchPhotoDataJob = null
                }
            }
        }
    }

}