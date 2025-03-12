package com.ludito.test.ui.utils

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Created by: androdev
 * Date: 13-03-2025
 * Time: 2:00 AM
 * Email: Kjuraev.001@gmail.com
 */

@Composable
fun UpDownAnimatedImage(
    modifier: Modifier,
    painter: Painter,
    travelDistance: Dp = 8.dp,
    durationMillis: Int = 700,
    contentDescription: String? = null,
    animate: Boolean = true
) {
    if (animate) {
        val infiniteTransition = rememberInfiniteTransition(label = "UpDownAnimation")

        val offsetY by infiniteTransition.animateValue(
            initialValue = 0.dp,
            targetValue = travelDistance,
            typeConverter = Dp.VectorConverter,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = durationMillis, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ), label = "UpDownAnimation"
        )

        Image(
            modifier = modifier.offset(y = -offsetY),
            painter = painter,
            contentDescription = contentDescription
        )
    } else {
        Image(
            modifier = modifier,
            painter = painter,
            contentDescription = contentDescription
        )
    }
}
