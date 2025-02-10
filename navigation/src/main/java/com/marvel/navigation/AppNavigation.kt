package com.marvel.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.marvel.navigation.graph.DetailScreens
import com.marvel.navigation.graph.detailGraph
import com.marvel.navigation.screens.Detail
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AppNavigation(
    navigator: Navigator,
    homeScreen: @Composable () -> Unit,
    detailScreen: @Composable (Int) -> Unit,
    detailScreenWithGraph: DetailScreens
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
        detailGraph(detailScreenWithGraph)
        composable(Destination.home.route) {
            homeScreen()
        }

        composable(Destination.detail.route, Destination.detail.arguments) {
            val id = Detail.objectParser(it)
            detailScreen(id)
        }
    }

}
