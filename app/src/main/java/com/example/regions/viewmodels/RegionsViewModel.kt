package com.example.regions.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.regions.RegionsRepository
import com.example.regions.models.RegionListDataItem
import com.example.regions.models.RepositoryResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegionsViewModel @Inject constructor(private val regionsRepository: RegionsRepository) :
    ViewModel() {

    private val _liststate =
        MutableStateFlow<RegionsApiResponseState>(RegionsApiResponseState.Loading)
    val listState = _liststate.asStateFlow()

    /**
     * make an api call to request region list
     */
    fun loadRegions() {
        viewModelScope.launch {
            _liststate.value =
                RegionsApiResponseState.Loading //reset the response state to default mode as loading
            regionsRepository.retrieveListOfRegions().catch { e ->
                e.cause?.let {
                    _liststate.value = RegionsApiResponseState.Error(it.localizedMessage ?: "")
                    Log.d(
                        RegionsViewModel::class.simpleName,
                        "viewmodel error occurred while fetching list response ${it.localizedMessage}"
                    )
                }
            }.collect { response ->
                when (response) {
                    is RepositoryResult.Success -> {
                        Log.d(
                            RegionsViewModel::class.simpleName,
                            "viewmodel success fetching list response ${response.data}"
                        )

                        _liststate.value = RegionsApiResponseState.Success(response.data)
                    }

                    is RepositoryResult.Error -> {
                        Log.d(
                            RegionsViewModel::class.simpleName,
                            "viewmodel error occurred while fetching list response ${response.errorString}"
                        )
                        _liststate.value = RegionsApiResponseState.Error(response.errorString)
                    }
                }
            }
        }
    }
}


/**
 * Type of response statuses
 */
sealed class RegionsApiResponseState {
    object Loading : RegionsApiResponseState()
    data class Success(val data: List<RegionListDataItem>) :
        RegionsApiResponseState()

    class Error(val errorStringRes: String) : RegionsApiResponseState()
}