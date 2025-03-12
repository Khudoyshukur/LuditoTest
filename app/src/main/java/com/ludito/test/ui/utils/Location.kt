package com.ludito.test.ui.utils

import android.location.Location
import com.ludito.test.model.SearchItem
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition

/**
 * Created by: androdev
 * Date: 12-03-2025
 * Time: 3:17 PM
 * Email: Kjuraev.001@gmail.com
 */

fun getDefaultCameraPosition(point: Point): CameraPosition {
    return CameraPosition(
        point, Constant.DEFAULT_ZOOM, Constant.DEFAULT_AZIMUTH, Constant.DEFAULT_TILT
    )
}

fun calculateDistance(point1: Point, point2: Point): Float {
    val results = FloatArray(1)
    Location.distanceBetween(
        point1.latitude,
        point1.longitude,
        point2.latitude,
        point2.longitude,
        results
    )
    return results[0]
}

fun findNearestSearchResult(
    point: Point,
    results: List<SearchItem>
): SearchItem? {
    var bestResult: SearchItem? = null
    var bestDistance = Float.MAX_VALUE

    for (result in results) {
        val distance = calculateDistance(point, result.point)
        if (distance < bestDistance) {
            bestDistance = distance
            bestResult = result
        }
    }

    return bestResult
}