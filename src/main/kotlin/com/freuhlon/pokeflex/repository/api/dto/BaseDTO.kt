package com.freuhlon.pokeflex.repository.api.dto

import com.beust.klaxon.Json

data class BaseDTO(
        @Json("HP")
        val hp: Int,
        @Json("Attack")
        val attack: Int,
        @Json("Defense")
        val defense: Int,
        @Json("Sp. Attack")
        val specialAttack: Int,
        @Json("Sp. Defense")
        val specialDefense: Int,
        @Json("Speed")
        val speed: Int
)
