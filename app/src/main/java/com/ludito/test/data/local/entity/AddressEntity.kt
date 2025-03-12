package com.ludito.test.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by: androdev
 * Date: 13-03-2025
 * Time: 12:04 AM
 * Email: Kjuraev.001@gmail.com
 */

@Entity(tableName = "saved_addresses")
data class AddressEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "lat") val lat: Double,
    @ColumnInfo(name = "lng") val lng: Double,
)