package com.ludito.test.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ludito.test.R

/**
 * Created by: androdev
 * Date: 12-03-2025
 * Time: 8:01 PM
 * Email: Kjuraev.001@gmail.com
 */

@Composable
fun MyLocationComponent(
    modifier: Modifier = Modifier.size(64.dp),
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .clickable { onClick() }
    ) {
        Image(
            modifier = Modifier
                .background(Color.White, CircleShape)
                .padding(18.dp),
            painter = painterResource(R.drawable.ic_tabler_location_filled),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview
@Composable
fun MyLocationComponentPreview() {
    MyLocationComponent(modifier = Modifier.size(64.dp))
}