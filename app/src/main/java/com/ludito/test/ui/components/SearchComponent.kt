package com.ludito.test.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ludito.test.R
import com.ludito.test.ui.theme.md_color_gray_218
import com.ludito.test.ui.theme.md_color_gray_220_04
import com.ludito.test.ui.theme.md_color_gray_244

/**
 * Created by: androdev
 * Date: 12-03-2025
 * Time: 5:06 PM
 * Email: Kjuraev.001@gmail.com
 */

@Composable
fun SearchComponent(
    modifier: Modifier = Modifier,
    editable: Boolean = true,
    query: String,
    onQueryChanged: (String) -> Unit = {},
    onFocusChanged: (FocusState) -> Unit = {}
) {
    Box(
        modifier = modifier.fillMaxWidth()
            .background(Color.White, RoundedCornerShape(10.dp))
            .border(1.dp, md_color_gray_220_04, RoundedCornerShape(10.dp))
            .padding(7.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth()
                .onFocusChanged { onFocusChanged(it) },
            value = query,
            onValueChange = onQueryChanged,
            readOnly = !editable,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.Black
                )
            },
            trailingIcon = if (editable) {
                {
                    Icon(
                        modifier = Modifier.clickable { onQueryChanged("") },
                        painter = painterResource(R.drawable.ic_carbon_close_filled),
                        contentDescription = null
                    )
                }
            } else null,
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = md_color_gray_218,
                unfocusedBorderColor = md_color_gray_218,
                focusedContainerColor = md_color_gray_244,
                unfocusedContainerColor = md_color_gray_244
            ),
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.W700,
                color = Color.Black
            ),
            shape = RoundedCornerShape(10.dp),
            placeholder = {
                Text(
                    text = stringResource(R.string.search),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700,
                    color = Color.Black
                )
            }
        )
    }
}

@Preview
@Composable
fun SearchComponentPreview() {
    SearchComponent(
        modifier = Modifier,
        query = "Some query entered",
        editable = true,
        onQueryChanged = {}
    )
}