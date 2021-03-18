package com.freuhlon.pokeflex.repository

import com.freuhlon.pokeflex.model.PokedexEntry

interface PokedexRepository {
    fun getAll(): List<PokedexEntry>
    fun reloadPokedex()
    fun getOne(number: Long): PokedexEntry?
}
