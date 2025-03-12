package com.ludito.test.ui.screens.search

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ludito.test.model.SearchItem
import com.ludito.test.ui.components.AddAddressConfirmationDialog
import com.ludito.test.ui.components.MyLocationComponent
import com.ludito.test.ui.components.SearchComponent
import com.ludito.test.ui.components.YandexMapComponent
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.VisibleRegion

/**
 * Created by: androdev
 * Date: 12-03-2025
 * Time: 2:33 PM
 * Email: Kjuraev.001@gmail.com
 */

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val searchQuery by viewModel.queryStream.collectAsStateWithLifecycle()
    val searchResults by viewModel.searchResultsStream.collectAsStateWithLifecycle(initialValue = emptyList())
    val selectedSearchResult by viewModel.selectedSearchResultStream.collectAsStateWithLifecycle()
    val currentMapLocation by viewModel.currentMapLocationStream.collectAsStateWithLifecycle()

    var saveAddressConfirmation by remember { mutableStateOf<SearchItem?>(null) }
    var showSearchBottomSheet by remember { mutableStateOf(false) }

    SearchScreenMainContent(
        currentMapLocation = currentMapLocation,
        selectedSearchResult = selectedSearchResult,
        isSearchBottomSheetShown = showSearchBottomSheet,
        showSearchBottomSheet = { showSearchBottomSheet = true },
        onChangeVisibleRegion = viewModel::onChangeVisibleRegion,
        onSearchLocation = viewModel::onSearchLocation
    )

    if (showSearchBottomSheet) {
        SearchBottomSheet(
            searchQuery = searchQuery,
            searchResults = searchResults,
            onChangeSearchQuery = viewModel::onChangeQuery,
            onSelectSearchResult = {
                showSearchBottomSheet = false
                viewModel.onSelectSearchResult(it)
                viewModel.onChangeQuery("")
            },
            onDismiss = {
                showSearchBottomSheet = false
                viewModel.onChangeQuery("")
            }
        )
    }

    selectedSearchResult?.let { searchItem ->
        SelectedSearchResultBottomSheet(
            searchItem = searchItem,
            onDismiss = viewModel::clearSelectedSearchResult,
            onSaveAddress = {
                saveAddressConfirmation = searchItem
                viewModel.clearSelectedSearchResult()
            }
        )
    }

    saveAddressConfirmation?.let {
        AddAddressConfirmationDialog(
            searchItem = it,
            onDismiss = { saveAddressConfirmation = null },
            onConfirm = {
                viewModel.saveAddress(it)
                saveAddressConfirmation = null
            }
        )
    }
}

@Composable
private fun SearchScreenMainContent(
    currentMapLocation: Point,
    selectedSearchResult: SearchItem?,
    isSearchBottomSheetShown: Boolean,
    showSearchBottomSheet: () -> Unit,
    onChangeVisibleRegion: (VisibleRegion) -> Unit,
    onSearchLocation: (CameraPosition) -> Unit
) {
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        YandexMapComponent(
            modifier = Modifier.fillMaxSize(),
            currentLocation = currentMapLocation,
            showPlacemark = true,
            onChangeVisibleRegion = onChangeVisibleRegion,
            onSearchLocation = onSearchLocation
        )

        AnimatedVisibility(
            visible = !isSearchBottomSheetShown,
            exit = fadeOut(),
            enter = fadeIn()
        ) {
            SearchComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                query = selectedSearchResult?.title.orEmpty(),
                editable = false,
                onFocusChanged = {
                    if (it.hasFocus) {
                        showSearchBottomSheet()
                    }
                }
            )
        }

        MyLocationComponent(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 40.dp)
                .size(64.dp),
            onClick = {
                Toast.makeText(context, "НЕТ В ЗАДАЧЕ", Toast.LENGTH_SHORT).show()
            }
        )
    }
}
