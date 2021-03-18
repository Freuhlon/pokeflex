package com.freuhlon.pokeflex.service

import com.freuhlon.pokeflex.model.PokedexEntry
import com.freuhlon.pokeflex.repository.PokedexRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class PokedexService(
        @Autowired
        val repository: PokedexRepository
) {
    fun getAll(): List<PokedexEntry> {
        return this.repository.getAll()
    }

    @PostConstruct
    fun reload() {
        this.repository.reloadPokedex()
    }

    fun getPokemon(number: Long): PokedexEntry? {
        return this.repository.getOne(number)
    }
}
