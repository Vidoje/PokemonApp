package com.taurunium.pokemon.kotlin.models

import com.squareup.moshi.Json

data class PokemonResponse(
    @Json(name = "results")
    val result: List<Pokemon>
)