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
import com.nathanyd.pokedex.ResultScreen
import com.nathanyd.pokedex.data.PokeDataResponse
import com.nathanyd.pokedex.ui.PokeUiState
import com.nathanyd.pokedex.ui.PokeViewModel
import com.nathanyd.pokedex.ui.screens.shared.ErrorScreen
import com.nathanyd.pokedex.ui.screens.shared.LoadingScreen
import com.nathanyd.pokedex.ui.screens.shared.PokeImage
import com.nathanyd.pokedex.ui.screens.shared.PokeName
import com.nathanyd.pokedex.ui.screens.shared.SearchBarLayout
import com.nathanyd.pokedex.ui.screens.shared.TopAppBarLayout
import com.nathanyd.pokedex.ui.theme.PokedexTheme

@Composable
fun DefaultAppScreen(
    pokeUiState: PokeUiState,
    onPokeClicked: () -> Unit,
) {
    when (pokeUiState) {
        is PokeUiState.Success -> DefaultHomeScreen(
            pokeUiState = pokeUiState,
            onPokeClicked = onPokeClicked,
            data = pokeUiState.getData
        )

        is PokeUiState.Error -> com.nathanyd.pokedex.ErrorScreen(modifier = Modifier.fillMaxSize())
        is PokeUiState.Loading -> com.nathanyd.pokedex.LoadingScreen(modifier = Modifier.fillMaxSize())
    }
}

//Home Page
@Composable
fun DefaultHomeScreen(
    pokeUiState: PokeUiState,
    onPokeClicked: () -> Unit,
    data: List<String>,
    modifier: Modifier = Modifier
) {
    var query by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }
    Scaffold(
        topBar = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                TopAppBarLayout()
                SearchBarLayout(
                    query = query,
                    onQueryChange = { query = it },
                    onSearch = { query = it },
                    active = active,
                    onActiveChange = { active = it },
                    modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
                )
            }
        },

        ) { innerPadding ->
        LazyColumn(modifier = modifier.padding(innerPadding)) {
            items(data) { pokemonName ->
                PokeCard(
                    nameOfPokemon = pokemonName,
                    onPokeClicked = onPokeClicked,
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
fun PokeCard(nameOfPokemon: String, onPokeClicked: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        onClick = onPokeClicked,
        modifier = modifier,
        shape = RoundedCornerShape(
            topStart = 50.dp,
            bottomStart = 15.dp,
            topEnd = 15.dp,
            bottomEnd = 50.dp
        )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            PokeImage(modifier = Modifier.size(84.dp))
            PokeName(name = nameOfPokemon, fontSize = 24.sp)
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