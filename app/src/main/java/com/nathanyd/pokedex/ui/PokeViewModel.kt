package com.nathanyd.pokedex.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nathanyd.pokedex.data.PokeDataResponse
import com.nathanyd.pokedex.network.PokeApi
import com.nathanyd.pokedex.network.PokeApi.pokemonService
import kotlinx.coroutines.launch
import java.io.IOException


sealed interface PokeUiState {
    data class Success(val getData: List<String>) : PokeUiState
    object Error : PokeUiState
    object Loading : PokeUiState
}

class PokeViewModel : ViewModel() {

    var pokeUiState: PokeUiState by mutableStateOf(PokeUiState.Loading)
        private set

    init {
        getPokeData()
    }

    fun getPokeData() {
        viewModelScope.launch {
            pokeUiState = try {
                val pokemonNames = mutableListOf<String>()
                for(i in 1..10) {
                    var listResult = pokemonService.getData("$i")
                    pokemonNames.add(listResult.name)
                }
                PokeUiState.Success(pokemonNames)
            } catch (e: IOException) {
                PokeUiState.Error
            }
        }
    }

}