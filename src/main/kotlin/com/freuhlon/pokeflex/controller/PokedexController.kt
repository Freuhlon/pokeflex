package com.freuhlon.pokeflex.controller

import com.freuhlon.pokeflex.model.PokedexEntry
import com.freuhlon.pokeflex.service.PokedexService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@Api( description="API pokedex.", tags = ["Pokémon"])
@RestController
class PokedexController(
        @Autowired
        val service: PokedexService
) {

    @ApiOperation("Retrieves all Pokémon from Pokedex")
    @GetMapping("/pokedex")
    fun getAll(): List<PokedexEntry> {
        return service.getAll()
    }

    @GetMapping("/pokedex/{number}")
    fun getOne(@PathVariable("number") number: Long): PokedexEntry? {
        return service.getPokemon(number)
    }

    @ApiOperation("Regenerates Pokémon DataBase")
    @GetMapping("/reload")
    fun reload(): ResponseEntity<Void> {
        service.reload()
        return ResponseEntity.ok().build()
    }
}
