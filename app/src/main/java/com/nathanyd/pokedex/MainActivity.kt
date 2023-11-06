package com.nathanyd.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nathanyd.pokedex.data.PokeDataResponse
import com.nathanyd.pokedex.ui.Navigation
import com.nathanyd.pokedex.ui.PokeUiState
import com.nathanyd.pokedex.ui.PokeViewModel
import com.nathanyd.pokedex.ui.screens.DefaultHomeScreen
import com.nathanyd.pokedex.ui.screens.DefaultPokeScreen
import com.nathanyd.pokedex.ui.screens.shared.PokeLayout
import com.nathanyd.pokedex.ui.theme.PokedexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun testScreen(
    pokeUiState: PokeUiState
) {
    when (pokeUiState) {
        is PokeUiState.Success -> ResultScreen(
            pokeUiState.getData, modifier = Modifier.fillMaxWidth()
        )
        is PokeUiState.Error -> ErrorScreen( modifier = Modifier.fillMaxSize())
        is PokeUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun ResultScreen(data: List<String>, modifier: Modifier = Modifier) {
    Column {
        data.forEach {
            Text(text = it)
        }
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Text("Error")
}


@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Text("Loading")
}
