package com.jnasser.weather.presentation.weather_detail.composables.wind

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jnasser.core.domain.enums.WindUnitsEnum
import com.jnasser.core.presentation.designsystem.theme.WeatherAppTheme

@Composable
fun WindUnitDropdown(
    modifier: Modifier = Modifier,
    units: List<WindUnitsEnum>
) {
    var expanded by remember { mutableStateOf(false) }

    var selectedUnit by remember { mutableStateOf(WindUnitsEnum.MILES) }

    Column(
        modifier = modifier
    ) {
        FilterChip(
            onClick = { expanded = !expanded },
            shape = RoundedCornerShape(25.dp),
            colors = FilterChipDefaults.filterChipColors(
                containerColor = MaterialTheme.colorScheme.onSurface.copy(0.2f)
            ),
            border = null,
            label = {
                Text(
                    text = selectedUnit.symbol,
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
                )
            },
            selected = expanded,
            trailingIcon = {
                if(expanded) {
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowUp,
                        tint = MaterialTheme.colorScheme.onSurface,
                        contentDescription = null
                    )
                } else {
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowDown,
                        tint = MaterialTheme.colorScheme.onSurface,
                        contentDescription = null
                    )
                }
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            units.forEach { unit ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = unit.symbol,
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
                        )
                    },
                    onClick = {
                        selectedUnit = unit
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun WindUnitDropdownPreview() {
    WeatherAppTheme {
        WindUnitDropdown(
            units = WindUnitsEnum.entries
        )
    }
}