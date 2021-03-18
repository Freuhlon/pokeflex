package com.freuhlon.pokeflex.repository.database.entity

import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "pokedex")
data class PokedexEntity(
        @Id
        val id: Long,
        val name: String,
        val type: String,
        val base: String
)
