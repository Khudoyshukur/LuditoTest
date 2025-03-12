package com.ludito.test.ui.screens.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Created by: androdev
 * Date: 12-03-2025
 * Time: 3:54 PM
 * Email: Kjuraev.001@gmail.com
 */

@Composable
fun ProfileScreen() {
    Scaffold {
        Box(Modifier.padding(it), contentAlignment = Alignment.Center) {
            Text(text = "НЕТ В ЗАДАЧЕ")
        }
    }
}