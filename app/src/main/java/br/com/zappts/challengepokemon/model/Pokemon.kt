package br.com.zappts.challengepokemon.model


data class Pokemon(
    val number: Int,
    val name: String,
    val types: List<PokeType>,
    val url: String,
    val id: String = url,
    val weight: Int,
    val height: Int,
)