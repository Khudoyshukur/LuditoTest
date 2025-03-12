package com.ludito.test.model

import com.ludito.test.data.local.entity.AddressEntity
import com.ludito.test.ui.utils.Constant
import com.yandex.mapkit.geometry.Point

/**
 * Created by: androdev
 * Date: 12-03-2025
 * Time: 11:43 PM
 * Email: Kjuraev.001@gmail.com
 */

data class SavedAddress(
    val id: Long,
    val title: String,
    val address: String,
    val point: Point
) {
    companion object {
        fun from(entity: AddressEntity): SavedAddress {
            return SavedAddress(
                id = entity.id,
                title = entity.title,
                address = entity.address,
                point = Point(entity.lat, entity.lng)
            )
        }
    }
}

val mockSavedAddress = SavedAddress(
    id = 1L,
    title = "Le Grande Plaza Hotel",
    address = "ул. Узбекистон Овози, 2",
    point = Constant.TASHKENT_LOCATION
)