@file:OptIn(ExperimentalMaterial3Api::class)

package com.nathanyd.pokedex.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nathanyd.pokedex.data.PokeData
import com.nathanyd.pokedex.data.PokeDataResponse
import com.nathanyd.pokedex.ui.Pages
import com.nathanyd.pokedex.ui.PokeUiState
import com.nathanyd.pokedex.ui.PokeViewModel
import com.nathanyd.pokedex.ui.screens.shared.ButtonSwitch
import com.nathanyd.pokedex.ui.screens.shared.ErrorScreen
import com.nathanyd.pokedex.ui.screens.shared.LoadingScreen
import com.nathanyd.pokedex.ui.screens.shared.PokeGif
import com.nathanyd.pokedex.ui.screens.shared.PokeImage
import com.nathanyd.pokedex.ui.screens.shared.PokeName
import com.nathanyd.pokedex.ui.screens.shared.SearchBarLayout
import com.nathanyd.pokedex.ui.screens.shared.TopAppBarLayout
import com.nathanyd.pokedex.ui.theme.PokedexTheme

@Composable
fun DefaultAppScreen(
    pokeUiState: PokeUiState,
    navController: NavController
) {
    when (pokeUiState) {
        is PokeUiState.Success -> DefaultHomeScreen(
            navController = navController,
            data = pokeUiState.getNames
        )

        is PokeUiState.Error -> ErrorScreen(onClick = {}) // onclick nog fixxen
        is PokeUiState.Loading -> LoadingScreen()
    }
}

//Home Page
@Composable
fun DefaultHomeScreen(
    navController: NavController,
    data: List<PokeData>,
    modifier: Modifier = Modifier
) {
    var querySearch by rememberSaveable { mutableStateOf("") }
    var activeSearch by rememberSaveable { mutableStateOf(false) }

    var isSwitched by rememberSaveable {
        mutableStateOf(false)
    }


    Scaffold(
        topBar = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                TopAppBarLayout()
                SearchBarLayout(
                    query = querySearch,
                    onQueryChange = { querySearch = it },
                    onSearch = { querySearch = it },
                    active = activeSearch,
                    onActiveChange = { activeSearch = it },
                    modifier = Modifier.padding(start = 24.dp, end = 24.dp),
                    pokeData = data
                )
                ButtonSwitch(
                    isSwitched = isSwitched,
                    onCheckChanged = { isSwitched = !isSwitched },
                    modifier = Modifier.padding(8.dp)
                )
            }
        },

        ) { innerPadding ->
        LazyColumn(modifier = modifier.padding(innerPadding)) {
            items(data) { pokemonData ->
                PokeCard(
                    isSwitched = isSwitched,
                    pokemonData = pokemonData,
                    navController = navController,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

//Pokemon Card
@Composable
fun PokeCard(
    isSwitched: Boolean,
    pokemonData: PokeData,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { navController.navigate("${Pages.PokeData.name}/${pokemonData.name}/${pokemonData.id}") },
        modifier = modifier,
        shape = RoundedCornerShape(
            topStart = 50.dp,
            bottomStart = 15.dp,
            topEnd = 15.dp,
            bottomEnd = 50.dp
        )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (isSwitched) PokeGif(name = pokemonData.name, Modifier.size(84.dp))
            if (!isSwitched) PokeImage(id = pokemonData.id, modifier = Modifier.size(84.dp))
            PokeName(name = pokemonData.name, fontSize = 24.sp)
        }
    }
}

//Pokemon Card Preview
@Preview(showBackground = true)
@Composable
fun PokeCardPreview() {
    PokedexTheme {
        //PokeCard()
    }
}