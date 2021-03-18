package com.freuhlon.pokeflex.repository.api.dto

data class PokedexDTO(
        val id: Long,
        val name: NameDTO,
        val type: List<String>,
        val base: BaseDTO
)
