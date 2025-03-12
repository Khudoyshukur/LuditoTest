package com.ludito.test.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.ludito.test.R
import com.ludito.test.ui.utils.UpDownAnimatedImage
import com.ludito.test.ui.utils.getDefaultCameraPosition
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.CameraUpdateReason
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.map.VisibleRegion
import com.yandex.mapkit.mapview.MapView

/**
 * Created by: androdev
 * Date: 12-03-2025
 * Time: 2:58 PM
 * Email: Kjuraev.001@gmail.com
 */

/**
 * Yandex maps does not support Jetpack compose yet.
 *
 * That's why using it with AndroidView.
 * Github issue: https://github.com/yandex/mapkit-android-demo/issues/317
 * */

@Composable
fun YandexMapComponent(
    modifier: Modifier,
    currentLocation: Point,
    showPlacemark: Boolean,
    onChangeVisibleRegion: (VisibleRegion) -> Unit = {},
    onSearchLocation: (CameraPosition) -> Unit = {}
) {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }
    var map by remember { mutableStateOf<Map?>(null) }
    var isCameraMoving by remember { mutableStateOf(false) }

    DisposableEffect(Unit) {
        mapView.onStart()
        onDispose { mapView.onStop() }
    }

    Box(modifier = modifier.fillMaxSize()) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { mapView },
            update = { map = it.map }
        )

        UpDownAnimatedImage(
            modifier = Modifier
                .size(74.dp)
                .offset(y = (-37).dp)
                .align(Alignment.Center),
            painter = painterResource(R.drawable.ic_pin),
            contentDescription = null,
            animate = isCameraMoving
        )
    }

    LaunchedEffect(currentLocation, map) {
        val position = getDefaultCameraPosition(currentLocation)
        map?.move(position)
    }

    DisposableEffect(map) {
        val listener = CameraListener { map, update, reason, finished ->
            if (finished) {
                onChangeVisibleRegion(map.visibleRegion)
            }

            if (reason == CameraUpdateReason.GESTURES && finished) {
                onSearchLocation(update)
            }

            isCameraMoving = !finished
        }
        map?.addCameraListener(listener)
        onDispose { map?.removeCameraListener(listener) }
    }

    LaunchedEffect(map, showPlacemark) {
        if (showPlacemark) {
            map?.mapObjects?.addPlacemark()
        }
    }
}