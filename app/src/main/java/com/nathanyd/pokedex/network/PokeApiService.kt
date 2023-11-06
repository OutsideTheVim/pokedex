package com.nathanyd.pokedex.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.nathanyd.pokedex.data.PokeDataResponse
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

//URL for the pokemon data API
private const val BASE_URL_DATA = "https://pokeapi.co/api/v2/"

//URL for the pokemon image API
private const val BASE_URL_IMG = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/1.png"

//building retrofit object
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL_DATA)
    .build()

// API endpoints
interface PokeApiService {
    @GET("pokemon/{pokemonId}")
    suspend fun getName(@Path("pokemonId") pokemonId: String): PokeDataResponse
}

// creating retrofit object
object PokeApi {
    val pokemonService : PokeApiService by lazy {
        retrofit.create(PokeApiService::class.java)
    }
}
