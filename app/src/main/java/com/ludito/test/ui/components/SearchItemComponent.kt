package com.ludito.test.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ludito.test.R
import com.ludito.test.model.SearchItem
import com.ludito.test.model.mockSearchItem
import com.ludito.test.ui.theme.md_color_text_disabled

/**
 * Created by: androdev
 * Date: 12-03-2025
 * Time: 8:40 PM
 * Email: Kjuraev.001@gmail.com
 */

@Composable
fun SearchItemComponent(
    modifier: Modifier = Modifier,
    item: SearchItem
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .background(Color.White)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(32.dp),
            painter = painterResource(R.drawable.ic_location_filled),
            contentDescription = null
        )

        Spacer(Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.title,
                fontWeight = FontWeight.W700,
                fontSize = 16.sp,
                color = Color.Black,
            )

            Spacer(Modifier.height(2.dp))

            Text(
                text = item.address,
                fontWeight = FontWeight.W700,
                fontSize = 16.sp,
                color = md_color_text_disabled
            )
        }

        Text(
            text = getDistanceText(item.distanceMeters),
            fontWeight = FontWeight.W700,
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}

@Composable
private fun getDistanceText(distanceMeters: Int): String {
    return when {
        distanceMeters <= 999 -> stringResource(R.string.distance_meters, distanceMeters)
        else -> stringResource(R.string.distance_kilometers, distanceMeters / 1000)
    }
}

@Preview
@Composable
fun SearchItemComponentPreview() {
    SearchItemComponent(
        modifier = Modifier,
        item = mockSearchItem
    )
}