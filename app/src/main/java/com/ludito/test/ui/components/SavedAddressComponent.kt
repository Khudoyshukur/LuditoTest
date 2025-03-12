package com.ludito.test.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ludito.test.R
import com.ludito.test.model.SavedAddress
import com.ludito.test.model.mockSavedAddress
import com.ludito.test.ui.theme.md_color_text_disabled

/**
 * Created by: androdev
 * Date: 13-03-2025
 * Time: 12:23 AM
 * Email: Kjuraev.001@gmail.com
 */

@Composable
fun SavedAddressComponent(
    modifier: Modifier,
    address: SavedAddress,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = address.title,
                fontWeight = FontWeight.W700,
                fontSize = 16.sp,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.height(2.dp))

            Text(
                text = address.address,
                fontWeight = FontWeight.W700,
                fontSize = 16.sp,
                color = md_color_text_disabled,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(Modifier.height(16.dp))

        Icon(
            modifier = Modifier.size(32.dp),
            painter = painterResource(R.drawable.ic_location_favourite),
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun SavedAddressComponentPreview() {
    SavedAddressComponent(
        modifier = Modifier,
        address = mockSavedAddress
    )
}