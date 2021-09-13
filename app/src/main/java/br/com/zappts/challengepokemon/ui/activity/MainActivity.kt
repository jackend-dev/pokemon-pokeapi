package br.com.zappts.challengepokemon.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.zappts.challengepokemon.R
import br.com.zappts.challengepokemon.databinding.ActivityMainBinding
import br.com.zappts.challengepokemon.ui.adapter.PokeViewAdapter
import br.com.zappts.challengepokemon.utils.Extensions.toast
import br.com.zappts.challengepokemon.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var pokeAdapter: PokeViewAdapter
    private lateinit var viewModel: MainActivityViewModel

    companion object {
        const val POKE_ID = "br.com.zappts.challengepokemon.ui.activity.MainActivity.POKE_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        pokeAdapter = PokeViewAdapter {
            val intent = Intent(this, PokeDetailsActivity::class.java)
            intent.putExtra(POKE_ID, it)
            startActivity(intent)
        }

        binding.rvListPokemons.adapter = pokeAdapter

        viewModel.getPokeObserver.observe(this, Observer {
            if (it != null) {
                pokeAdapter.addItemInList(it)
            } else {
                toast("Erro ao carregar dados")
            }
        })

        viewModel.apiCallListPoke()
    }
}