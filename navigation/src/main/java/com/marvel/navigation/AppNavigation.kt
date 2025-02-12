package com.marvel.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.marvel.domain.models.CharacterEntity
import com.marvel.navigation.screens.Detail
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AppNavigation(
    navigator: Navigator,
    homeScreen: @Composable () -> Unit,
    detailScreen: @Composable (CharacterEntity) -> Unit
) {
    val navController = rememberNavController()

    LaunchedEffect(Unit) {
        navigator.actions.collectLatest { action ->
            when (action) {
                Navigator.Action.Back -> navController.navigateUp()
                is Navigator.Action.Navigate -> navController.navigate(
                    route = action.destination,
                    builder = action.navOptions
                )
            }
        }
    }

    NavHost(navController, startDestination = Destination.home.route) {
        composable(Destination.home.route) {
            homeScreen()
        }

        composable(Destination.detail.route, Destination.detail.arguments) { entry ->
            val character = Detail.objectParser(entry)
            detailScreen(character)
        }
    }
}
