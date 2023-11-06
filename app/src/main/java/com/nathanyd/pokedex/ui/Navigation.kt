package com.nathanyd.pokedex.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nathanyd.pokedex.ui.screens.DefaultHomeScreen
import com.nathanyd.pokedex.ui.screens.DefaultPokeScreen
import com.nathanyd.pokedex.ui.screens.shared.TopAppBarLayout

//all pages in the app
enum class Pages {
    Home,
    PokeData
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val currentRoute by remember {
        mutableStateOf(navController.currentBackStackEntry?.destination?.route)
    }
    NavHost(navController = navController, startDestination = Pages.Home.name) {
        composable(Pages.Home.name) {
            DefaultHomeScreen(
                onPokeClicked = {
                    navController.navigate(Pages.PokeData.name)
                })
        }
        composable(Pages.PokeData.name) {
            DefaultPokeScreen()
        }
    }
}