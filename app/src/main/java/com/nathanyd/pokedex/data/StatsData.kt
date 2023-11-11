package com.nathanyd.pokedex.data

import kotlinx.serialization.Serializable

@Serializable
data class Stats(
    val base_stat: Int,
    val stat: StatDetails
)

@Serializable
data class StatDetails(
    val name: String
)