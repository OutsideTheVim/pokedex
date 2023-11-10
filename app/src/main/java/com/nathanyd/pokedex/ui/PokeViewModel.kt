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
                    pokemonNames.add(PokeData(name = data.name, id = data.id))
                    pokeUiState = PokeUiState.Loading(i, _amount)
                }

                PokeUiState.Success(pokemonNames)
            } catch (e: IOException) {
                PokeUiState.Error
            }
        }
    }

}