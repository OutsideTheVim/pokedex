package com.nathanyd.pokedex.data

import kotlinx.serialization.Serializable

//model for JSON data
@Serializable
data class PokeDataResponse(
    val name: String,
    val id: Int,
    val types: List<Type>,
    val stats: List<Stats>
)

data class PokeData (
    val name: String,
    val id: Int,
    val types: List<Type>,
    val stats: List<Stats>? = null
)
