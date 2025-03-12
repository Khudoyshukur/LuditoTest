package com.ludito.test.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ludito.test.R
import com.ludito.test.model.SearchItem
import com.ludito.test.model.mockSearchItem

/**
 * Created by: androdev
 * Date: 13-03-2025
 * Time: 1:29 AM
 * Email: Kjuraev.001@gmail.com
 */

@Composable
fun AddAddressConfirmationDialog(
    searchItem: SearchItem,
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {}
) {
    Dialog(
        onDismissRequest = onDismiss,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .background(Color.White, RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(R.string.save_address_to_favorite),
                fontWeight = FontWeight.W700,
                fontSize = 20.sp
            )

            Spacer(Modifier.height(26.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = searchItem.address,
                    fontWeight = FontWeight.W700,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.width(16.dp))

                Icon(
                    painter = painterResource(R.drawable.ic_edit),
                    contentDescription = null
                )
            }
            
            Spacer(Modifier.height(16.dp))
            HorizontalDivider()

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(
                    modifier = Modifier.weight(1f),
                    onClick = onDismiss
                ) {
                    Text(
                        text = stringResource(R.string.cancel),
                        fontWeight = FontWeight.W700,
                        fontSize = 20.sp,
                        color = Color.Red
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                TextButton(
                    onClick = onConfirm
                ) {
                    Text(
                        text = stringResource(R.string.confirm),
                        fontWeight = FontWeight.W700,
                        fontSize = 20.sp,
                        color = Color.Black,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun AddAddressConfirmationDialogPreview() {
    AddAddressConfirmationDialog(
        searchItem = mockSearchItem
    )
}