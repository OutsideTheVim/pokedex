package com.nathanyd.pokedex.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//model for JSON data
@Serializable
data class PokeDataResponse(
    val name: String
)
