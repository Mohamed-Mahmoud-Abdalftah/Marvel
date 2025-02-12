package com.marvel.navigation.screens

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.marvel.domain.models.CharacterEntity
import com.marvel.navigation.utils.ArgsScreen
import com.marvel.navigation.utils.DestinationRoute
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.collections.List

object Detail : ArgsScreen<CharacterEntity> {
    override val route: String = "detail/{character}"
    override val arguments: List<NamedNavArgument> =
        listOf(navArgument("character") { type = NavType.StringType })

    override fun objectParser(entry: NavBackStackEntry): CharacterEntity {
        val json = entry.arguments?.getString("character") ?: return CharacterEntity(0, "", "", "", "", emptyList())
        return Json.decodeFromString(json)
    }

    override fun destination(arg: CharacterEntity): DestinationRoute {
        val json = Json.encodeToString(arg)
        return "detail/$json"
    }
}
