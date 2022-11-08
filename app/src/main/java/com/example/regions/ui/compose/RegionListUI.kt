package com.example.regions.ui.compose

import android.graphics.Typeface
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.regions.R
import com.example.regions.models.RegionListDataItem
import com.example.regions.viewmodels.RegionsViewModel

@Preview
@Composable
fun RegionListUI(listRegions: List<RegionListDataItem>, viewModel: RegionsViewModel) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Column(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxHeight(),
            ) {
                // displays list
                LazyColumn {

                    listRegions.forEach { country ->
                        item {
                            RegionListItem(country, viewModel)
                        }
                    }

                }

            }
        }
    }
}


/**
 * List item for a regions list
 */
@Preview
@Composable
fun RegionListItem(country: RegionListDataItem, viewModel: RegionsViewModel) {

    val context = LocalContext.current
    Card(
        elevation = 4.dp,
        backgroundColor = colorResource(id = R.color.secondary),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 24.dp, horizontal = 24.dp)
                .fillMaxWidth()
        ) {

            Column(modifier = Modifier.fillMaxWidth(0.8f)) {
                Text(
                    text = "${country.name} (${country.region})",
                    fontFamily = FontFamily(Typeface.DEFAULT_BOLD),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 5.dp)
                )

                Text(
                    text = country.capital,
                    fontStyle = FontStyle.Italic,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 5.dp),
                    color = colorResource(id = R.color.secondary_dark)
                )
            }
            Spacer(modifier = Modifier.weight(0.5f))

            Text(
                text = country.code,
                fontFamily = FontFamily(Typeface.DEFAULT),
                color = colorResource(id = R.color.secondary_dark),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

