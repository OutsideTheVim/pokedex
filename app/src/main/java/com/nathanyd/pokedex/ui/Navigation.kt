package com.nathanyd.pokedex.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nathanyd.pokedex.ui.screens.DefaultHomeScreen
import com.nathanyd.pokedex.ui.screens.DefaultPokeScreen

//all pages in the app
enum class Pages {
    Home,
    PokeData
}

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Pages.Home.name) {
        composable(Pages.Home.name) {
            DefaultHomeScreen(onPokeClicked = {
                navController.navigate(Pages.PokeData.name)
            })
        }
        composable(Pages.PokeData.name) {
            DefaultPokeScreen()
        }
    }
}