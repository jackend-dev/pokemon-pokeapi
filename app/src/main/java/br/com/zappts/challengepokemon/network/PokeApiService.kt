package br.com.zappts.challengepokemon.network

import br.com.zappts.challengepokemon.model.Pokemon
import br.com.zappts.challengepokemon.model.PokeListResult
import br.com.zappts.challengepokemon.network.PokeApiConst.LIMIT
import br.com.zappts.challengepokemon.network.PokeApiConst.LIMIT_QUERY
import br.com.zappts.challengepokemon.network.PokeApiConst.OFFSET
import br.com.zappts.challengepokemon.network.PokeApiConst.OFFSET_QUERY
import br.com.zappts.challengepokemon.network.PokeApiConst.PATH_POKE_ID
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {

    // lista de pokemons
    @GET("pokemon")
    fun listPokemon(
        @Query(LIMIT_QUERY) limit: Int = LIMIT,
        @Query(OFFSET_QUERY) offset: Int = OFFSET
    ): Call<PokeListResult>

    // buscar o id do pokemon
    @GET("pokemon/{$PATH_POKE_ID}")
    fun getPokemonById(
        @Path(PATH_POKE_ID) id: Int,
    ): Call<Pokemon>
}