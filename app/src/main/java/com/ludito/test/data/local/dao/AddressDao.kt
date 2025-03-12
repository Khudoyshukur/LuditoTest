package com.ludito.test.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ludito.test.data.local.entity.AddressEntity

/**
 * Created by: androdev
 * Date: 13-03-2025
 * Time: 12:06 AM
 * Email: Kjuraev.001@gmail.com
 */

@Dao
interface AddressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(address: AddressEntity)

    @Query("DELETE FROM saved_addresses WHERE id=:id")
    suspend fun delete(id: Long)

    @Query("SELECT * FROM saved_addresses")
    fun getAllPaging(): PagingSource<Int, AddressEntity>
}