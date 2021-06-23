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

class PokemonAdapter(
    private var pokemons: List<Pokemon>
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder =
        PokemonViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        )

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemons[position])
    }

    override fun getItemCount(): Int = pokemons.size

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var tvName: TextView
        lateinit var name: String
        lateinit var url: String

        fun bind(pokemon: Pokemon) {
            tvName = itemView.findViewById(R.id.list_item_pokemon_name)

            name = pokemon.name
            url = pokemon.url

            tvName.text = name

            itemView.setOnClickListener {
                val bundle = bundleOf(
                    "name" to name,
                    "url" to url
                )
                it.findNavController().navigate(R.id.action_fragmentList_to_fragmentDetail, bundle)
            }
        }
    }
}