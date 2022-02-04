package com.taurunium.pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.taurunium.pokemon.java.activities.PokemonDetailsActivity
import com.taurunium.pokemon.kotlin.adapters.PokemonListAdapter

import com.taurunium.pokemon.kotlin.network.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.pokemonsLiveData.observe(this, { pokemons ->
            val adapter = PokemonListAdapter(pokemons)
            val recyclerView = findViewById<RecyclerView>(R.id.rvPokemons)
            recyclerView?.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            recyclerView.adapter = adapter

            adapter.setOnItemListener(object : PokemonListAdapter.onItemClickListener {
                override fun onItemClick(position: Int) {
                    val intent = Intent(this@MainActivity, PokemonDetailsActivity::class.java)
                    intent.putExtra("num", position + 1)
                    startActivity(intent)
                }
            })
        })
    }
}