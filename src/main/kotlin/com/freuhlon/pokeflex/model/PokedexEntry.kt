package com.freuhlon.pokeflex.model


data class PokedexEntry(
        val id: Long,
        val name: Name,
        val type: List<String>,
        val base: Base
)

data class Name(
        val english: String,
        val japanese: String,
        val chinese: String,
        val french: String
)

data class Base(
        val hp: Int,
        val attack: Int,
        val defense: Int,
        val specialAttack: Int,
        val specialDefense: Int,
        val speed: Int
)
