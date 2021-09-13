package br.com.zappts.challengepokemon.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.zappts.challengepokemon.model.Pokemon
import br.com.zappts.challengepokemon.network.PokeApiInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokeDetailsActivityViewModel : ViewModel() {
    private val _pokeDetailResponse: MutableLiveData<Pokemon?> = MutableLiveData()

    val getDetailPoke: MutableLiveData<Pokemon?>
        get() = _pokeDetailResponse


    fun getApiCallDetailPoke( id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val callPokeDetail = PokeApiInstance.apiResponse.getPokemonById(id)
            callPokeDetail.enqueue(object : Callback<Pokemon> {
                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                    _pokeDetailResponse.postValue(response.body())
                }

                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    Log.d("TAG", "Erro")
                }

            })
        }
    }
}