package com.nathanyd.pokedex.data

import kotlinx.serialization.Serializable

@Serializable
data class Type(
    val type: TypeDetails
)

@Serializable
data class TypeDetails(
    val name: String
)

//model for JSON data
@Serializable
data class PokeDataResponse(
    val name: String,
    val id: Int,
    val types: List<Type>
)

data class PokeData (
    val name: String,
    val id: Int,
    val types: List<Type>
)
