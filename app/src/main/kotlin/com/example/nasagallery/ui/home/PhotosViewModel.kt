package com.example.nasagallery.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasagallery.common.DataState
import com.example.nasagallery.data.model.PhotoDetails
import com.example.nasagallery.data.repository.PhotosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val repository: PhotosRepository
): ViewModel() {

    private val _photoDetailsState = MutableStateFlow<DataState<List<PhotoDetails>>>(DataState.Empty)
    val photoDetailsState = _photoDetailsState.asStateFlow()

    init {
        fetchPhotos()
    }

    fun fetchPhotos() {
        viewModelScope.launch {
            _photoDetailsState.value = DataState.Loading
            try {
                val result = withContext(Dispatchers.IO) {
                    repository.getPhotos()
                }
                Log.wtf("fetchPhotos",result.toString())
                delay(500)
                _photoDetailsState.value = DataState.Success(result)
            } catch (error: Throwable) {
                _photoDetailsState.value = DataState.Error(error)
            }
        }
    }

}