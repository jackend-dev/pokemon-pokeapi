package br.com.zappts.challengepokemon.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.zappts.challengepokemon.model.PokeListResult
import br.com.zappts.challengepokemon.model.Pokemon
import br.com.zappts.challengepokemon.network.PokeApiInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {
    private val _pokeListResponse : MutableLiveData<List<Pokemon>?> = MutableLiveData()

    val getPokeObserver: MutableLiveData<List<Pokemon>?>
        get() = _pokeListResponse

    // retorna os resultados na chamada da api
    fun apiCallListPoke() {
        viewModelScope.launch(Dispatchers.IO) {
            val callPoke = PokeApiInstance.apiResponse.listPokemon()

            callPoke.enqueue(object : Callback<PokeListResult> {
                override fun onResponse(call: Call<PokeListResult>, response: Response<PokeListResult>) {
                    _pokeListResponse.postValue(response.body()?.results)
                }

                override fun onFailure(call: Call<PokeListResult>, t: Throwable) {
                    Log.d("E", "Erro")
                }

            })
        }
    }
}