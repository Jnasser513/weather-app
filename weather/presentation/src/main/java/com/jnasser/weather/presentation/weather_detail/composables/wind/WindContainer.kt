package com.jnasser.weather.presentation.weather_detail.composables.wind

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.jnasser.core.domain.enums.WindUnitsEnum
import com.jnasser.core.presentation.designsystem.components.WindFieldOverlay
import com.jnasser.core.presentation.designsystem.theme.WeatherAppTheme
import com.jnasser.core.presentation.designsystem.theme.WeatherDarkBlue
import com.jnasser.core.presentation.designsystem.theme.WeatherGrey
import com.jnasser.weather.presentation.R
import com.jnasser.weather.presentation.weather_detail.WeatherDetailAction
import com.jnasser.weather.presentation.weather_detail.model.WindDataUi

@Composable
fun WindContainer(
    modifier: Modifier = Modifier,
    windDataUi: WindDataUi,
    windUnit: WindUnitsEnum,
    onAction: (WeatherDetailAction) -> Unit
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(Color.Transparent)
            .border(width = 2.dp, color = WeatherGrey, shape = RoundedCornerShape(25.dp))
    ) {
        val (title, map, home, gust, windDirection, windUnits) = createRefs()

        Box(
            modifier = Modifier.constrainAs(map) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }.fillMaxSize()
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.2f),
                painter = painterResource(R.drawable.map),
                contentDescription = stringResource(R.string.map_image),
                contentScale = ContentScale.FillBounds
            )

            WindFieldOverlay(
                modifier = Modifier.fillMaxSize(),
                streakCount = 100,
                targetPoint = Offset(0.25f, 0.2f)
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                Color.Transparent,
                                WeatherDarkBlue.copy(0.2f)
                            ),
                            center = Offset.Unspecified,
                            radius = 500f
                        )
                    )
            )
        }

        Text(
            modifier = Modifier
                .constrainAs(title) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .padding(10.dp), text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(stringResource(R.string.wind))
                }
                append(" ")
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Light,
                        fontSize = 13.sp
                    )
                ) {
                    append(windDataUi.title)
                }
            }
        )
        WindGustData(
            modifier = Modifier
                .constrainAs(gust) {
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                }
                .padding(10.dp),
            velocity = windDataUi.velocity,
            gust = windDataUi.gust,
            windUnit = windUnit
        )
        HomeIcon(
            modifier = Modifier.constrainAs(home) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        )
        WindDirectionIndicator(
            modifier = Modifier.constrainAs(windDirection) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            }.padding(10.dp),
            direction = "ESE"
        )
        WindUnitDropdown(
            modifier = Modifier.constrainAs(windUnits) {
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }.padding(horizontal = 10.dp, vertical = 5.dp),
            units = WindUnitsEnum.entries
        ) { unit ->
            onAction(WeatherDetailAction.OnChangeWindUnit(unit))
        }
    }
}

@Preview
@Composable
private fun WindContainerPreview() {
    WeatherAppTheme {
        WindContainer(
            windDataUi = WindDataUi(
                title = "Gentle Breeze", direction = "ESE", velocity = "9", gust = "14"
            ),
            windUnit = WindUnitsEnum.MILES
        ) {}
    }
}