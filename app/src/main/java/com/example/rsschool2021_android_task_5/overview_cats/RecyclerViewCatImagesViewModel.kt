package com.example.rsschool2021_android_task_5.overview_cats

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rsschool2021_android_task_5.COUNT_OF_CAT_IMAGES_REQUEST_FROM_API
import com.example.rsschool2021_android_task_5.HAS_BREEDS_API_PARAMETER_REQUEST
import com.example.rsschool2021_android_task_5.PagingSource
import com.example.rsschool2021_android_task_5.network.CatsApi
import com.example.rsschool2021_android_task_5.network.CatsApiService
import com.example.rsschool2021_android_task_5.network.CatsApiStatus
import com.example.rsschool2021_android_task_5.network.CatsProperty
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RecyclerViewCatImagesViewModel : ViewModel() {

    private val _status = MutableLiveData<CatsApiStatus>()
    val status: LiveData<CatsApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<CatsProperty>>()
    val properties: LiveData<List<CatsProperty>>
        get() = _properties

    val data: StateFlow<PagingData<CatsProperty>> = Pager(PagingConfig(pageSize = COUNT_OF_CAT_IMAGES_REQUEST_FROM_API)) {
        PagingSource(CatsApi.retrofitService)
    }.flow
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    init {
        getCatProperties()
    }

    private fun getCatProperties() {
        viewModelScope.launch {
            _status.value = CatsApiStatus.LOADING
            try {
                _properties.value = CatsApi.retrofitService.getProperties(
                    COUNT_OF_CAT_IMAGES_REQUEST_FROM_API, HAS_BREEDS_API_PARAMETER_REQUEST)
                _status.value = CatsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = CatsApiStatus.ERROR
                _properties.value = ArrayList()
                Log.e("CONNECTION_TO_CAT_API", e.stackTraceToString())
            }
        }
    }
}