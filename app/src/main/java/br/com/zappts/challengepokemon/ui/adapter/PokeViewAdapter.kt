package br.com.zappts.challengepokemon.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zappts.challengepokemon.databinding.PokeItemBinding
import br.com.zappts.challengepokemon.ui.holder.PokeViewHolder
import br.com.zappts.challengepokemon.model.Pokemon
import com.bumptech.glide.Glide
import java.util.*


class PokeViewAdapter(
    val pokeClickListener: (Int) -> Unit
) : RecyclerView.Adapter<PokeViewHolder>() {

    // lista mutável da classe modelo Pokemon
    val pokeList: MutableList<Pokemon> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PokeItemBinding.inflate(inflater, parent, false)

        return PokeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokeViewHolder, position: Int) {
        val item = pokeList[position]


        holder.let {
            val numberId = item.url
                .replace("https://pokeapi.co/api/v2/pokemon/", "")
                .replace("/", "").toInt()

            // formatar a string para começar com letra maiúscula
            it.binding.name = item.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            }

            // carregar a imagem de cada pokemon
            Glide.with(it.binding.root)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/" +
                        "pokemon/${numberId}.png")
                .into(it.binding.ivImagePokemon)
            it.binding.ivImagePokemon.setOnClickListener {
                pokeClickListener(numberId)
            }

        }
    }

    override fun getItemCount() = pokeList.size

    fun addItemInList(items: List<Pokemon>) {
        pokeList.addAll(items)

        // notifcação de mudanças, nem sempre eficiente
        notifyDataSetChanged()
    }


}