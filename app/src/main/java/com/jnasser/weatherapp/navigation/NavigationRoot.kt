package com.jnasser.weatherapp.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.city.presentation.city_saved_list.components.CitySavedListScreenRoot
import com.example.city.presentation.city_search.composables.CitySearchScreenRoot
import com.jnasser.weather.presentation.weather_detail.composables.WeatherDetailScreenRoot
import kotlinx.serialization.Serializable

@Serializable
data object CityGraphRoute

@Serializable
data object CitySavedListRoute

@Serializable
data object CitySearchRoute

@Serializable
data class WeatherDetailRoute(
    val cityId: String
)

@Composable
fun NavigationRoot(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        NavHost(
            navController = navController,
            startDestination = CityGraphRoute
        ) {
            cityGraph(navController)

            composable<WeatherDetailRoute>(
                enterTransition = {
                    fadeIn(animationSpec = tween(250))
                },
                exitTransition = {
                    fadeOut(animationSpec = tween(250))
                },
                popEnterTransition = {
                    fadeIn(animationSpec = tween(250))
                },
                popExitTransition = {
                    fadeOut(animationSpec = tween(250))
                }
            ) { backStackEntry ->
                val route = backStackEntry.toRoute<WeatherDetailRoute>()

                WeatherDetailScreenRoot(
                    lat = route.lat,
                    lon = route.lon,
                    goHome = {
                        navController.navigate(CitySavedListRoute) {
                            popUpTo(CityGraphRoute) {
                                inclusive = false
                            }
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

private fun NavGraphBuilder.cityGraph(navController: NavHostController) {
    navigation<CityGraphRoute>(
        startDestination = CitySavedListRoute
    ) {
        composable<CitySavedListRoute>(
            enterTransition = {
                fadeIn(animationSpec = tween(250))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(250))
            }
        ) {
            CitySavedListScreenRoot(
                onCityDetail = { lat, lon ->
                    navController.navigate(WeatherDetailRoute(lat, lon))
                },
                onCitySearch = {
                    navController.navigate(CitySearchRoute)
                }
            )
        }

        composable<CitySearchRoute>(
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = tween(300)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(300)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(300)
                )
            }
        ) {
            CitySearchScreenRoot(
                onCityDetail = { lat, lon ->
                    navController.navigate(WeatherDetailRoute(lat, lon))
                },
                onReturn = {
                    navController.navigateUp()
                }
            )
        }
    }
}