package com.taurunium.pokemon.kotlin.network

import com.taurunium.pokemon.kotlin.models.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("pokemon")
    fun fetchPokemons(@Query("limit") limit: String): Call<PokemonResponse>
}