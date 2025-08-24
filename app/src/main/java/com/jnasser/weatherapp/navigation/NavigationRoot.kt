package com.jnasser.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.city.presentation.city_saved_list.components.CitySavedListScreenRoot
import com.example.city.presentation.city_search.composables.CitySearchScreenRoot
import kotlinx.serialization.Serializable

@Serializable data object CityGraphRoute

@Composable
fun NavigationRoot(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = CityGraphRoute
    ) {
        cityGraph(navController)
    }
}

@Serializable data object CitySavedListRoute
@Serializable data object CitySearchRoute

private fun NavGraphBuilder.cityGraph(navController: NavHostController) {
    navigation<CityGraphRoute>(
        startDestination = CitySavedListRoute
    ) {
        composable<CitySavedListRoute> {
            CitySavedListScreenRoot(
                onCityDetail = { id ->
                    // TODO("Navigate to city detail")
                },
                onCitySearch = { navController.navigate(CitySearchRoute) }
            )
        }

        composable<CitySearchRoute> {
            CitySearchScreenRoot(
                onReturn = {
                    navController.navigateUp()
                }
            )
        }
    }
}