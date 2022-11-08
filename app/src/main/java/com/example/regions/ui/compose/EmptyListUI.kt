package com.example.regions.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.regions.R

/**
 * On Error show this UI + the Toast
 */
@Composable
fun EmptyListUI() {

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = colorResource(id = R.color.secondary)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                stringResource(R.string.details_are_empty), modifier = Modifier
                    .padding(22.dp)
                    .align(Alignment.Center),
                fontStyle = FontStyle.Italic,
                color = colorResource(id = R.color.secondary_dark),
                fontSize = 22.sp
            )
        }
    }
}
