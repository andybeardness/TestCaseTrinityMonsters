package andy.beardness.testcasetrinitymosters.adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import andy.beardness.testcasetrinitymosters.R
import andy.beardness.testcasetrinitymosters.model.Pokemon

class PokemonAdapter(private var pokemons: List<Pokemon>) :
    RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder =
        PokemonViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        )


    override fun getItemCount(): Int = pokemons.size


    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemons[position])
        Log.d("ABC", "pos == $position")
    }

    fun updatePokemons(pokemons: List<Pokemon>) {
        this.pokemons = pokemons
        notifyDataSetChanged()
    }

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var tvName: TextView
        lateinit var url: String

        fun bind(pokemon: Pokemon) {
            tvName = itemView.findViewById(R.id.list_item_pokemon_name)
            tvName.text = pokemon.name
            url = pokemon.url
            itemView.setOnClickListener {
                val bundle = bundleOf(
                    "name" to pokemon.name,
                    "url" to pokemon.url
                )
                it.findNavController().navigate(R.id.action_fragmentList_to_fragmentDetail, bundle)
            }
        }
    }
}