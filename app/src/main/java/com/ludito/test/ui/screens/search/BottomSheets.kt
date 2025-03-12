package com.ludito.test.ui.screens.search

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ludito.test.model.SearchItem
import com.ludito.test.ui.components.SearchPlacesComponent
import com.ludito.test.ui.components.SelectedSearchResultComponent

/**
 * Created by: androdev
 * Date: 13-03-2025
 * Time: 1:28 AM
 * Email: Kjuraev.001@gmail.com
 */

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SearchBottomSheet(
    searchQuery: String,
    searchResults: List<SearchItem>,
    onDismiss: () -> Unit,
    onChangeSearchQuery: (String) -> Unit,
    onSelectSearchResult: (SearchItem) -> Unit
) {
    ModalBottomSheet(
        containerColor = Color.White,
        scrimColor = Color.Transparent,
        windowInsets = WindowInsets(
            top = 56.dp,
            left = 0.dp,
            right = 0.dp,
            bottom = 0.dp
        ),
        sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true
        ),
        onDismissRequest = { onDismiss() },
    ) {
        SearchPlacesComponent(
            modifier = Modifier,
            query = searchQuery,
            onQueryChanged = onChangeSearchQuery,
            searchResults = searchResults,
            onSearchResultSelected = onSelectSearchResult
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SelectedSearchResultBottomSheet(
    searchItem: SearchItem,
    onDismiss: () -> Unit,
    onSaveAddress: () -> Unit,
) {
    ModalBottomSheet(
        containerColor = Color.White,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        onDismissRequest = { onDismiss() },
    ) {
        SelectedSearchResultComponent(
            searchItem = searchItem,
            onDismiss = onDismiss,
            onSaveAddress = onSaveAddress
        )
    }
}