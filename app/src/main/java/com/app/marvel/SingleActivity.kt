package com.app.marvel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.app.marvel.ui.theme.MarvelTheme
import com.marvel.detail.presentation.detailScreen.DetailScreen
import com.marvel.home.presentation.HomeScreen
import com.marvel.navigation.AppNavigation
import com.marvel.navigation.Navigator

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SingleActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       enableEdgeToEdge()
        setContent {
            MarvelTheme {
                AppNavigation(
                    navigator = navigator,
                    homeScreen = {
                        HomeScreen()
                    },
                    detailScreen = {
                      DetailScreen(it)
                    },
                )
            }
        }
    }
}