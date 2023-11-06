package com.nathanyd.pokedex.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.nathanyd.pokedex.data.PokeDataResponse
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL_DATA = "https://pokeapi.co/api/v2/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL_DATA)
    .build()

interface PokeApiService {
    @GET("pokemon/{pokemonName}")
    suspend fun getData(@Path("pokemonName") pokemonName: String): PokeDataResponse
}

object PokeApi {
    val pokemonService : PokeApiService by lazy {
        retrofit.create(PokeApiService::class.java)
    }
}
