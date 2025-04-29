package com.jnasser.weather.presentation.weather_detail.composables.forecast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jnasser.core.presentation.designsystem.theme.WeatherAppTheme
import com.jnasser.weather.presentation.R
import com.jnasser.weather.presentation.weather_detail.model.ForecastDataUi

@Composable
fun ForecastContainer(
    modifier: Modifier = Modifier
) {
   Column(
       modifier = modifier.fillMaxWidth()
   ) {
       Row(
           modifier = Modifier
               .fillMaxWidth(),
           horizontalArrangement = Arrangement.SpaceBetween,
           verticalAlignment = Alignment.CenterVertically
       ) {
           Text(
               text = stringResource(R.string.forecast),
               style = MaterialTheme.typography.labelLarge.copy(
                   fontWeight = FontWeight.SemiBold
               )
           )
           ForecastOptions(
               onDailyClick = {

               },
               onHourlyClick = {

               }
           )
       }
       Spacer(Modifier.height(10.dp))
       ForecastList(
           forecastList = listOf(
               ForecastDataUi(
                   title = "Today",
                   icon = "https://openweathermap.org/img/wn/10d@2x.png",
                   maxTemperature = "48",
                   minTemperature = "56",
                   progress = 0.25f
               ),
               ForecastDataUi(
                   title = "Thu",
                   icon = "https://openweathermap.org/img/wn/10d@2x.png",
                   maxTemperature = "48",
                   minTemperature = "56",
                   progress = 0.48f
               ),
               ForecastDataUi(
                   title = "Fri",
                   icon = "https://openweathermap.org/img/wn/10d@2x.png",
                   maxTemperature = "48",
                   minTemperature = "56",
                   progress = 0.91f
               )
           )
       )
   }
}

@Preview
@Composable
private fun ForecastContainerPreview() {
    WeatherAppTheme {
        ForecastContainer()
    }
}