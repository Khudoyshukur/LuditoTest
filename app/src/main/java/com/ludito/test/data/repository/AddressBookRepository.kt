package com.ludito.test.data.repository

import androidx.paging.PagingData
import com.ludito.test.model.SavedAddress
import com.ludito.test.model.SearchItem
import kotlinx.coroutines.flow.Flow

/**
 * Created by: androdev
 * Date: 12-03-2025
 * Time: 11:42 PM
 * Email: Kjuraev.001@gmail.com
 */

interface AddressBookRepository {
    fun getSavedAddressesPaging(): Flow<PagingData<SavedAddress>>
    suspend fun removeAddress(id: Long)
    suspend fun insertAddress(searchItem: SearchItem)
}