package com.jnasser.weather.presentation.weather_detail.composables.wind

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jnasser.core.domain.enums.WindUnitsEnum
import com.jnasser.core.presentation.designsystem.theme.WeatherAppTheme

@Composable
fun WindUnitDropdown(
    modifier: Modifier = Modifier,
    units: List<WindUnitsEnum>,
    onSelectUnit: (WindUnitsEnum) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    var selectedUnit by remember { mutableStateOf(WindUnitsEnum.MILES) }

    val rotation by animateFloatAsState(
        targetValue = if(expanded) 180f else 0f
    )

    Column(
        modifier = modifier
    ) {
        FilterChip(
            modifier = Modifier.defaultMinSize(minWidth = 80.dp),
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
                Icon(
                    modifier = Modifier.rotate(rotation),
                    imageVector = Icons.Outlined.KeyboardArrowDown,
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = null
                )
            }
        )

        DropdownMenu(
            shape = RoundedCornerShape(5.dp),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            units.forEach { unit ->
                DropdownMenuItem(
                    modifier = if(selectedUnit == unit) Modifier.background(MaterialTheme.colorScheme.onSurface.copy(0.2f)) else Modifier,
                    text = {
                        Text(
                            text = unit.symbol,
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
                        )
                    },
                    onClick = {
                        selectedUnit = unit
                        onSelectUnit(unit)
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
        ) {}
    }
}