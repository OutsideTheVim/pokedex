package com.nathanyd.pokedex.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nathanyd.pokedex.ui.screens.DefaultAppScreen
import com.nathanyd.pokedex.ui.screens.DefaultPokeScreen
import com.nathanyd.pokedex.ui.screens.VerifyPokeScreen

//all pages in the app
enum class Pages {
    Home, PokeData
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Pages.Home.name) {
        composable(Pages.Home.name) {
            val pokeViewModel: PokeViewModel = viewModel()
            DefaultAppScreen(
                navController = navController,
                pokeUiState = pokeViewModel.pokeUiState
            )
        }
        composable(
            "${Pages.PokeData.name}/{pokeId}",
            arguments = listOf(
                navArgument("pokeId") { type = NavType.IntType }
            )
        ) {

            val pokeId = it.arguments?.getInt("pokeId")

            val pokeViewModel: PokeScreenViewModel = viewModel()

            VerifyPokeScreen(
                pokeUiState = pokeViewModel.pokeScreenUiState,
                navController = navController,
                pokeId = pokeId
            )
        }
    }
}