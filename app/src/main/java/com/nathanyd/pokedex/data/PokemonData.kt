package com.nathanyd.pokedex.data

import kotlinx.serialization.Serializable

//model for JSON data
@Serializable
data class PokeDataResponse(
    val name: String,
    val id: Int
)

data class PokeData (
    val name: String,
    val id: Int
)
