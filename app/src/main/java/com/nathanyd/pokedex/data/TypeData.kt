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
