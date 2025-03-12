package com.ludito.test.data.repository.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.ludito.test.data.local.dao.AddressDao
import com.ludito.test.data.repository.AddressBookRepository
import com.ludito.test.model.SavedAddress
import com.ludito.test.model.SearchItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by: androdev
 * Date: 13-03-2025
 * Time: 12:12 AM
 * Email: Kjuraev.001@gmail.com
 */

class AddressBookRepositoryImpl @Inject constructor(
    private val addressDao: AddressDao
) : AddressBookRepository {
    override fun getSavedAddressesPaging(): Flow<PagingData<SavedAddress>> {
        val pager = Pager(
            config = PagingConfig(pageSize = 50)
        ) { addressDao.getAllPaging() }.flow

        return pager.map { data -> data.map { SavedAddress.from(it) } }
    }

    override suspend fun removeAddress(id: Long) {
        addressDao.delete(id)
    }

    override suspend fun insertAddress(searchItem: SearchItem) {
        addressDao.insert(searchItem.toAddressEntity())
    }
}