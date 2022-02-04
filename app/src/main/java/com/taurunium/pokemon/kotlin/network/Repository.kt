package com.taurunium.pokemon.kotlin.network

class Repository(private val apiService: ApiService) {
    fun getPokemons(limit: String) = apiService.fetchPokemons("20")
}