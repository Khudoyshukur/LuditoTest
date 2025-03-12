package com.ludito.test.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ludito.test.data.repository.AddressBookRepository
import com.ludito.test.data.repository.SearchRepository
import com.ludito.test.model.SearchItem
import com.ludito.test.ui.utils.Constant
import com.ludito.test.ui.utils.findNearestSearchResult
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.VisibleRegion
import com.yandex.mapkit.map.VisibleRegionUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by: androdev
 * Date: 12-03-2025
 * Time: 10:14 PM
 * Email: Kjuraev.001@gmail.com
 */

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    private val addressBookRepository: AddressBookRepository
) : ViewModel() {
    private var currentVisibleRegionStream: VisibleRegion? = null

    private val _queryStream = MutableStateFlow("")
    val queryStream get() = _queryStream.asStateFlow()

    private val _selectedSearchResultStream = MutableStateFlow<SearchItem?>(null)
    val selectedSearchResultStream get() = _selectedSearchResultStream.asStateFlow()

    private val _currentMapLocationStream = MutableStateFlow(Constant.TASHKENT_LOCATION)
    val currentMapLocationStream get() = _currentMapLocationStream.asStateFlow()

    val searchResultsStream
        get() = queryStream.mapLatest { query ->
            currentVisibleRegionStream?.let { visibleRegion ->
                searchRepository.search(
                    query = query,
                    visiblePolygon = VisibleRegionUtils.toPolygon(visibleRegion)
                )
            }.orEmpty()
        }

    fun onChangeQuery(newQuery: String) {
        viewModelScope.launch { _queryStream.emit(newQuery) }
    }

    fun onChangeVisibleRegion(region: VisibleRegion) {
        currentVisibleRegionStream = region
    }

    fun onSelectSearchResult(searchItem: SearchItem) {
        viewModelScope.launch {
            _selectedSearchResultStream.emit(searchItem)
            _currentMapLocationStream.emit(searchItem.point)
        }
    }

    fun clearSelectedSearchResult() {
        viewModelScope.launch { _selectedSearchResultStream.emit(null) }
    }

    fun saveAddress(searchItem: SearchItem) {
        viewModelScope.launch { addressBookRepository.insertAddress(searchItem) }
    }

    fun onSearchLocation(position: CameraPosition) {
        viewModelScope.launch {
            val results = searchRepository.search(position)

            _selectedSearchResultStream.emit(
                findNearestSearchResult(position.target, results)?.copy(point = position.target)
            )
        }
    }
}