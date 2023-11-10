package com.nathanyd.pokedex.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nathanyd.pokedex.data.PokeData
import com.nathanyd.pokedex.data.PokeDataResponse
import com.nathanyd.pokedex.network.PokeApi
import com.nathanyd.pokedex.network.PokeApi.pokemonService
import kotlinx.coroutines.launch
import java.io.IOException

//status screens
sealed interface PokeUiState {
    data class Success(val getNames: List<PokeData>) : PokeUiState
    object Error : PokeUiState
    data class Loading(val count: Int, val amount: Int) : PokeUiState
}

class PokeViewModel : ViewModel() {

    //Amount of pokemons that needs to be loaded
    private var _amount by mutableIntStateOf(20)

    var pokeUiState: PokeUiState by mutableStateOf(PokeUiState.Loading(0,_amount))
        private set

    init {
        getPokeData()
    }

    fun getPokeData() {
        pokeUiState = PokeUiState.Loading(0, _amount)
        viewModelScope.launch {
            pokeUiState = try {
                val pokemonNames = mutableListOf<PokeData>()

                for (i in 1.._amount) {
                    var data = pokemonService.getName("$i")

                    pokemonNames.add(PokeData(name = data.name, id = data.id, types = data.types))
                    pokeUiState = PokeUiState.Loading(i, _amount)
                }

                PokeUiState.Success(pokemonNames)
            } catch (e: IOException) {
                PokeUiState.Error
            }
        }
    }

    fun getTypeColor(type: String): Long {
        return when(type) {
            "normal" -> 0xFFA8A77A
            "fire" -> 0xFFEE8130
            "water" -> 0xFF6390F0
            "electric" -> 0xFFF7D02C
            "grass" -> 0xFF7AC74C
            "ice" -> 0xFF96D9D6
            "fighting" -> 0xFFC22E28
            "poison" -> 0xFFA33EA1
            "ground" -> 0xFFE2BF65
            "flying" -> 0xFFA98FF3
            "psychic" -> 0xFFF95587
            "bug" -> 0xFFA6B91A
            "rock" -> 0xFFB6A136
            "ghost" -> 0xFF735797
            "dragon" -> 0xFF6F35FC
            "dark" -> 0xFF6F35FC
            "steel" -> 0xFFB7B7CE
            "fairy" -> 0xFFD685AD
            else -> {
                0xFF080100
            }
        }
    }

}