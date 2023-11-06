package com.nathanyd.pokedex.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokeDataResponse(
    val name: String
)
