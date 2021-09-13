package br.com.zappts.challengepokemon.model

data class PokemonResult(
    val name: String,
    val url: String,
    val number: Int,
    val types: List<PokeType>
)