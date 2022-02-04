package com.taurunium.pokemon.kotlin.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.taurunium.pokemon.R
import com.taurunium.pokemon.kotlin.models.Pokemon
import com.taurunium.pokemon.kotlin.utils.Constants

class PokemonListAdapter(val pokemonsList: List<Pokemon>) :
    RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    inner class ViewHolder(private val itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

        fun bindData(pokemon: Pokemon) {
            val nameTextView = itemView.findViewById<TextView>(R.id.name)
            val ivPokemonImage = itemView.findViewById<ImageView>(R.id.image)

            nameTextView.text = pokemon.name

            val urlParts = pokemon.url.split("/")
            val imageName = urlParts[urlParts.size - 2]

            Picasso.get().load(Constants.BASE_IMAGE_URL + "" + imageName + ".png")
                .placeholder(R.drawable.ic_launcher_foreground).into(ivPokemonImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false),
            mListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(pokemonsList[position])
    }

    override fun getItemCount(): Int {
        return pokemonsList.size
    }

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemListener(listener: onItemClickListener){
        mListener = listener
    }
}