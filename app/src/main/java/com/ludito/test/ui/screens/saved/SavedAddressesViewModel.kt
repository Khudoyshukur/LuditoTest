package com.ludito.test.ui.screens.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ludito.test.data.repository.AddressBookRepository
import com.ludito.test.model.SavedAddress
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by: androdev
 * Date: 13-03-2025
 * Time: 12:45 AM
 * Email: Kjuraev.001@gmail.com
 */

@HiltViewModel
class SavedAddressesViewModel @Inject constructor(
    private val addressBookRepository: AddressBookRepository
): ViewModel() {
    val savedAddressesStream get() = addressBookRepository.getSavedAddressesPaging()

    fun onDeleteAddress(savedAddress: SavedAddress) {
        viewModelScope.launch { addressBookRepository.removeAddress(savedAddress.id) }
    }
}