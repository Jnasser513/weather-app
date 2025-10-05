package com.example.city.presentation.city_search.composables

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.city.presentation.R
import com.example.city.presentation.city_search.model.CitySearchUI
import com.jnasser.core.domain.extensions.toggle
import com.jnasser.core.presentation.designsystem.theme.WeatherAppTheme
import com.jnasser.core.presentation.designsystem.theme.WeatherWhite

@Composable
fun CitySearchCard(
    city: CitySearchUI,
    onSaveClick: () -> Unit,
    onDetailClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if(expanded) 180f else 0f,
        animationSpec = tween(durationMillis = 400, easing = LinearOutSlowInEasing),
        label = "iconRotation"
    )

    Card(shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF3A3A4D)),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "${city.primaryText}, ${city.secondaryText}",
                    color = WeatherWhite,
                    fontSize = 18.sp
                )
                Icon(
                    modifier = Modifier
                        .padding(vertical = 15.dp)
                        .rotate(rotation),
                    imageVector = Icons.Filled.ArrowDropDown,
                    tint = WeatherWhite,
                    contentDescription = null
                )
            }
            if(expanded) {
                Row(
                    modifier = Modifier
                        .animateContentSize(
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioHighBouncy,
                                stiffness = Spring.StiffnessLow
                            )
                        )
                        .fillMaxWidth()
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.secondary
                        ),
                        onClick = onSaveClick
                    ) {
                        Text(
                            text = stringResource(R.string.save),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Spacer(Modifier.width(10.dp))
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.secondary
                        ),
                        onClick = onDetailClick
                    ) {
                        Text(
                            text = stringResource(R.string.detail),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CitySearchCardPreview() {
    WeatherAppTheme {
        CitySearchCard(
            city = CitySearchUI(
                "1",
                "San Salvador",
                "El salvador"
            ),
            onSaveClick = {},
            onDetailClick = {}
        )
    }
}