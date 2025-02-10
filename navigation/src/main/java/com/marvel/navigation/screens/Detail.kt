package com.marvel.navigation.screens

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.marvel.navigation.utils.ArgsScreen
import com.marvel.navigation.utils.DestinationRoute
import kotlin.collections.List

object Detail : ArgsScreen<Int> {
    override val route: String = "detail/{id}"
    override val arguments: List<NamedNavArgument> =
        listOf(navArgument("id") { type = NavType.IntType})


    override fun objectParser(entry: NavBackStackEntry): Int =
        entry.arguments?.getInt("id") ?: 0

    override fun destination(arg: Int): DestinationRoute = "detail/$arg"
}

