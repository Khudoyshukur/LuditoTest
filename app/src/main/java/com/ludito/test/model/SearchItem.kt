package com.ludito.test.model

import com.ludito.test.data.local.entity.AddressEntity
import com.ludito.test.ui.utils.Constant
import com.yandex.mapkit.GeoObject
import com.yandex.mapkit.geometry.Point

/**
 * Created by: androdev
 * Date: 12-03-2025
 * Time: 8:40 PM
 * Email: Kjuraev.001@gmail.com
 */

data class SearchItem(
    val title: String,
    val address: String,
    val distanceMeters: Int,
    val point: Point
) {
    fun toAddressEntity(): AddressEntity {
        return AddressEntity(
            title = title,
            address = address,
            lat = point.latitude,
            lng = point.longitude
        )
    }

    companion object {
        fun from(geoObject: GeoObject): SearchItem? {
            val point = geoObject.geometry.firstOrNull()?.point ?: return null

            return SearchItem(
                title = geoObject.name.orEmpty(),
                address = geoObject.descriptionText.orEmpty(),
                distanceMeters = 0,
                point = point
            )
        }
    }
}

val mockSearchItem = SearchItem(
    title = "Le Grande Plaza Hotel",
    address = "Ташкент, ул. Узбекистон Овози, 2",
    distanceMeters = 36,
    point = Constant.TASHKENT_LOCATION
)