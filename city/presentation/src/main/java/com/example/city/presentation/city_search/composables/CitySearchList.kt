package com.example.city.presentation.city_search.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.city.presentation.city_search.model.CitySearchUI

@Composable
fun CitySearchList(
    modifier: Modifier = Modifier,
    cityList: List<CitySearchUI>
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(cityList) { item ->
            CitySearchCard(item) {

            }
        }
    }
}