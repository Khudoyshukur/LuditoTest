package com.ludito.test.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ludito.test.R
import com.ludito.test.model.SearchItem
import com.ludito.test.model.mockSearchItem
import com.ludito.test.ui.theme.md_color_button
import com.ludito.test.ui.theme.md_color_rating_active
import com.ludito.test.ui.theme.md_color_rating_inactive
import com.ludito.test.ui.theme.md_color_text_disabled

/**
 * Created by: androdev
 * Date: 12-03-2025
 * Time: 11:02 PM
 * Email: Kjuraev.001@gmail.com
 */

@Composable
fun SelectedSearchResultComponent(
    modifier: Modifier = Modifier,
    searchItem: SearchItem,
    onDismiss: () -> Unit = {},
    onSaveAddress: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .background(Color.White)
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Row {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = searchItem.title,
                    fontWeight = FontWeight.W700,
                    fontSize = 16.sp,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = searchItem.address,
                    fontWeight = FontWeight.W700,
                    fontSize = 16.sp,
                    color = md_color_text_disabled,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(Modifier.width(16.dp))
            Image(
                modifier = Modifier.size(24.dp).clip(CircleShape)
                    .clickable { onDismiss() },
                painter = painterResource(R.drawable.ic_carbon_close_filled),
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // making the rating static, because we do not have that info
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RandomRatingBar()

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = stringResource(R.string.rate, 517),
                style = MaterialTheme.typography.titleMedium
            )
        }

        Button(
            onClick = onSaveAddress,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = md_color_button
            )
        ) {
            Text(
                text = stringResource(R.string.save_address),
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
private fun RandomRatingBar() {
    val rating = (3..5).random()
    Row {
        for (i in 1..5) {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 4.dp),
                tint = if (i <= rating) md_color_rating_active else md_color_rating_inactive,
                painter = painterResource(R.drawable.ic_rating),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun SelectedSearchResultComponentPreview() {
    SelectedSearchResultComponent(
        searchItem = mockSearchItem
    )
}