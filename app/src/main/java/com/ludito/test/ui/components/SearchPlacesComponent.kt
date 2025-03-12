package com.ludito.test.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ludito.test.R
import com.ludito.test.model.SearchItem
import com.ludito.test.model.mockSearchItem

/**
 * Created by: androdev
 * Date: 12-03-2025
 * Time: 8:35 PM
 * Email: Kjuraev.001@gmail.com
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchPlacesComponent(
    modifier: Modifier,
    query: String,
    onQueryChanged: (String) -> Unit = {},
    searchResults: List<SearchItem>,
    onSearchResultSelected: (SearchItem) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier.fillMaxSize().background(Color.White)
    ) {
        stickyHeader {
            Box(
                modifier = Modifier.fillMaxWidth().background(Color.White)
            ) {
                SearchComponent(
                    modifier = Modifier.fillMaxWidth().padding(
                        bottom = 16.dp,
                        start = 16.dp,
                        end = 16.dp,
                    ),
                    query = query,
                    onQueryChanged = onQueryChanged,
                    editable = true,
                )
            }
        }

        if (searchResults.isNotEmpty()) {
            item { HorizontalDivider() }
            items(searchResults) { searchItem ->
                SearchItemComponent(
                    modifier = Modifier.clickable { onSearchResultSelected(searchItem) },
                    item = searchItem
                )
                HorizontalDivider()
            }
        } else {
            item {
                Text(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    text = stringResource(R.string.no_results),
                    fontWeight = FontWeight.W700,
                    fontSize = 16.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
fun SearchPlacesComponentPreview() {
    SearchPlacesComponent(
        modifier = Modifier,
        query = "Poisk",
        onQueryChanged = {},
        searchResults = List(5) { mockSearchItem },
        onSearchResultSelected = {}
    )
}

@Preview
@Composable
fun SearchPlacesComponentEmptyPreview() {
    SearchPlacesComponent(
        modifier = Modifier,
        query = "Poisk",
        onQueryChanged = {},
        searchResults = listOf(),
        onSearchResultSelected = {}
    )
}