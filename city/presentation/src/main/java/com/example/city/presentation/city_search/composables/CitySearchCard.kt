package com.example.city.presentation.city_search.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.city.presentation.city_search.model.CitySearchUI

@Composable
fun CitySearchCard(city: CitySearchUI, onClick: () -> Unit) {
    Card(shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF3A3A4D)),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "${city.primaryText}, ${city.secondaryText}",
                color = Color.White,
                fontSize = 18.sp
            )
            Icon(
                modifier = Modifier.padding(vertical = 15.dp),
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null
            )
        }
    }
}