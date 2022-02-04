package com.taurunium.pokemon.kotlin.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.taurunium.pokemon.kotlin.models.Pokemon
import com.taurunium.pokemon.kotlin.models.PokemonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(
    private val repository: Repository
    = Repository(ApiClient.apiService)
) : ViewModel() {

    private var _pokemonsLiveData = MutableLiveData<List<Pokemon>>()

    val pokemonsLiveData: LiveData<List<Pokemon>>
        get() = _pokemonsLiveData

    init {
        fetchPokemons()
    }

    private fun fetchPokemons(){
        val client = repository.getPokemons("20")
        client.enqueue(object : Callback<PokemonResponse>{
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                if(response.isSuccessful){
                    _pokemonsLiveData.postValue(response.body()?.result)
                }
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                Log.d("Failure", "onFailure: ")
            }
        })
    }
}