@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.city.presentation.city_search.composables

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.city.presentation.city_search.CitySearchAction
import com.example.city.presentation.city_search.CitySearchEvents
import com.example.city.presentation.city_search.CitySearchViewModel
import com.example.city.presentation.city_search.CitySearchViewState
import com.example.city.presentation.city_search.model.CitySearchUI
import com.jnasser.core.presentation.designsystem.components.WeatherAppScaffold
import com.jnasser.core.presentation.designsystem.components.WeatherAppSearchBar
import com.jnasser.core.presentation.designsystem.components.WeatherAppSearchBarConfig
import com.jnasser.core.presentation.designsystem.theme.WeatherAppTheme
import com.jnasser.core.presentation.ui.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel

@Composable
fun CitySearchScreenRoot(
    viewModel: CitySearchViewModel = koinViewModel(),
    onCityDetail: () -> Unit,
    onReturn: () -> Unit
) {
    val context = LocalContext.current

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is CitySearchEvents.Error -> Toast.makeText(
                context,
                event.error.asString(context),
                Toast.LENGTH_SHORT
            ).show()

            CitySearchEvents.OnReturn -> onReturn()
        }
    }

    CitySearchScreen(
        state = viewModel.state,
        onAction = { action ->
            when(action) {
                is CitySearchAction.OnCityDetail -> onCityDetail()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun CitySearchScreen(
    state: CitySearchViewState,
    onAction: (CitySearchAction) -> Unit
) {
    WeatherAppScaffold(
        modifier = Modifier
            .fillMaxSize(),
        withGradient = false
    ) { padding ->
        val searchBarState = rememberTextFieldState()

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(20.dp)
            ) {
                WeatherAppSearchBar(
                    textFieldState = searchBarState,
                    config = WeatherAppSearchBarConfig(
                        onSearch = { query ->
                            onAction(CitySearchAction.OnSearch(query))
                        }
                    )
                )
                CitySearchList(
                    cityList = state.suggestions,
                    onDetailClick = { item ->
                        onAction(CitySearchAction.OnCityDetail(0.0, 0.0))
                    },
                    onSaveClick = { item ->

                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun CitySearchScreenPreview() {
    WeatherAppTheme {
        CitySearchScreen(
            state = CitySearchViewState(
                suggestions = listOf(
                    CitySearchUI(
                        "1",
                        "San Salvador, El Salvador"
                    ),
                    CitySearchUI(
                        "2",
                        "San Marino, Islas"
                    ),
                    CitySearchUI(
                        "1",
                        "San Sebastian, Espana"
                    )
                )
            ),
            onAction = {

            }
        )
    }
}