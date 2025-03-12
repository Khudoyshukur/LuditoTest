package com.ludito.test.ui.screens.saved

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.ludito.test.R
import com.ludito.test.model.SavedAddress
import com.ludito.test.ui.components.RemoveAddressConfirmationDialog
import com.ludito.test.ui.components.SavedAddressComponent
import com.ludito.test.ui.theme.md_color_gray_249

/**
 * Created by: androdev
 * Date: 12-03-2025
 * Time: 3:55 PM
 * Email: Kjuraev.001@gmail.com
 */

@Composable
fun SavedAddressesScreen(
    viewModel: SavedAddressesViewModel = hiltViewModel()
) {
    val savedAddresses = viewModel.savedAddressesStream.collectAsLazyPagingItems()

    SavedAddressesScreenContent(
        savedAddresses = savedAddresses,
        onDeleteAddress = viewModel::onDeleteAddress
    )
}

@Composable
private fun SavedAddressesScreenContent(
    savedAddresses: LazyPagingItems<SavedAddress>,
    onDeleteAddress: (SavedAddress) -> Unit = {}
) {
    var addressToDelete by remember { mutableStateOf<SavedAddress?>(null) }

    Scaffold(
        containerColor = md_color_gray_249,
        topBar = { TopBar() }
    ) { paddingValues ->
        AddressesList(
            modifier = Modifier.padding(paddingValues),
            savedAddresses = savedAddresses,
            onAddressClicked = { addressToDelete = it }
        )
    }

    addressToDelete?.let {
        RemoveAddressConfirmationDialog(
            onDismiss = { addressToDelete = null },
            onConfirm = {
                val copy = addressToDelete
                addressToDelete = null

                copy?.let(onDeleteAddress)
            }
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopBar() {
    CenterAlignedTopAppBar(
        windowInsets = WindowInsets(0),
        modifier = Modifier.shadow(elevation = 1.dp),
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
        title = {
            Text(
                text = stringResource(R.string.my_addresses),
                fontWeight = FontWeight.W700,
                fontSize = 24.sp,
                color = Color.Black
            )
        }
    )
}

@Composable
private fun AddressesList(
    modifier: Modifier,
    savedAddresses: LazyPagingItems<SavedAddress>,
    onAddressClicked: (SavedAddress) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { Spacer(Modifier.height(0.dp)) }

        items(
            count = savedAddresses.itemCount,
            key = savedAddresses.itemKey { address -> address.id }
        ) { index ->
            Surface(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                shadowElevation = 3.dp,
                shape = RoundedCornerShape(12.dp)
            ) {
                SavedAddressComponent(
                    modifier = Modifier.fillMaxWidth(),
                    address = savedAddresses[index]!!,
                    onClick = { onAddressClicked(savedAddresses[index]!!) }
                )
            }
        }

        item { Spacer(Modifier.height(0.dp)) }
    }
}