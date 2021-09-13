package br.com.zappts.challengepokemon.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.zappts.challengepokemon.databinding.ActivityPokeDetailsBinding
import br.com.zappts.challengepokemon.model.Pokemon
import br.com.zappts.challengepokemon.ui.activity.MainActivity.Companion.POKE_ID
import br.com.zappts.challengepokemon.ui.adapter.PokeViewAdapter
import br.com.zappts.challengepokemon.utils.Extensions.toast
import br.com.zappts.challengepokemon.viewmodel.PokeDetailsActivityViewModel
import com.bumptech.glide.Glide
import java.util.*

class PokeDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPokeDetailsBinding
    private lateinit var viewModel: PokeDetailsActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokeDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this).get(PokeDetailsActivityViewModel::class.java)

        val pokeId = intent.getIntExtra(POKE_ID, -1)

        observerPoke()
        initViewModel(pokeId)
    }

    // iniciar a viewModel recebendo o id do pokemon selecionado
    fun initViewModel(id: Int) {
        viewModel.getApiCallDetailPoke(id)
    }

    // setar os detalhes do pokemon
    private fun setDetailPoke(it: Pokemon) {

        binding.apply {
            Glide.with(root)
                .load(
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/" +
                            "pokemon/${it.id}.png"
                )
                .into(ivPokemonImageDetail)

            tvNameDetails.text = it.name.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                }
            tvType.text = it.types[0].name
            tvWeight.text = "Peso: ${it.weight.toDouble()} kg"
            tvHeight.text = "Altura: ${it.height.toDouble()} cm"
        }
    }

    fun observerPoke() {
        viewModel.getDetailPoke.observe(this, Observer {
            if (it != null) {
                setDetailPoke(it)
            } else {
                toast("Erro ao carregar dados")
            }
        })
    }

}