package com.ludito.test.data.repository

import com.ludito.test.model.SearchItem
import com.yandex.mapkit.geometry.Geometry
import com.yandex.mapkit.map.CameraPosition

/**
 * Created by: androdev
 * Date: 12-03-2025
 * Time: 10:20 PM
 * Email: Kjuraev.001@gmail.com
 */

interface SearchRepository {
    suspend fun search(
        query: String,
        visiblePolygon: Geometry
    ): List<SearchItem>

    suspend fun search(
        position: CameraPosition
    ): List<SearchItem>
}