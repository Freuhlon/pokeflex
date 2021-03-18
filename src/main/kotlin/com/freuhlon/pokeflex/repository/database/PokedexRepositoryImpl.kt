package com.freuhlon.pokeflex.repository.database

import com.beust.klaxon.Klaxon
import com.freuhlon.pokeflex.model.Base
import com.freuhlon.pokeflex.model.Name
import com.freuhlon.pokeflex.model.PokedexEntry
import com.freuhlon.pokeflex.repository.PokedexRepository
import com.freuhlon.pokeflex.repository.api.dto.BaseDTO
import com.freuhlon.pokeflex.repository.api.dto.NameDTO
import com.freuhlon.pokeflex.repository.api.dto.PokedexDTO
import com.freuhlon.pokeflex.repository.database.entity.PokedexEntity
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.net.URL

@Repository
class PokedexRepositoryImpl(
        @Autowired
        val repository: PokedexJPARepository) : PokedexRepository {

    override fun getAll(): List<PokedexEntry> {
        return this.repository
                .findAll()
                .map { mapToDomain(it) }
    }

    override fun reloadPokedex() {
        val url = "https://raw.githubusercontent.com/fanzeyi/pokemon.json/master/pokedex.json"
        val readText: String = URL(url).readText()
        val pokedex: List<PokedexDTO> = Klaxon().parseArray<PokedexDTO>(readText).orEmpty()
        val map: Iterable<PokedexEntity> = pokedex.map { mapToEntity(it) }.asIterable()
        this.repository.deleteAll()
        this.repository.saveAll(map)
    }

    override fun getOne(number: Long): PokedexEntry? {
        val entity: PokedexEntity = this.repository.findById(number).orElseGet(null) ?: return null

        val names = Klaxon().parse<NameDTO>(entity.name)!!
        val bases = Klaxon().parse<BaseDTO>(entity.base)!!

        return PokedexEntry(
                id = entity.id,
                name = Name(
                        english = names.english,
                        chinese = names.chinese,
                        japanese = names.japanese,
                        french = names.french,
                ),
                base = Base(
                        speed = bases.speed,
                        attack = bases.attack,
                        specialAttack = bases.specialAttack,
                        specialDefense = bases.specialDefense,
                        hp = bases.hp,
                        defense = bases.defense
                ),
                type = Klaxon().parseArray<String>(entity.type).orEmpty()
        )

    }

    private fun mapToDomain(entity: PokedexEntity): PokedexEntry {
        val names = Klaxon().parse<NameDTO>(entity.name)!!
        val bases = Klaxon().parse<BaseDTO>(entity.base)!!
        return PokedexEntry(
                id = entity.id,
                name = Name(
                        english = names.english,
                        chinese = names.chinese,
                        japanese = names.japanese,
                        french = names.french,

                        ),
                base = Base(
                        speed = bases.speed,
                        attack = bases.attack,
                        specialAttack = bases.specialAttack,
                        specialDefense = bases.specialDefense,
                        hp = bases.hp,
                        defense = bases.defense
                ),
                type = Klaxon().parseArray<String>(entity.type).orEmpty()
        )
    }

    private fun mapToEntity(dto: PokedexDTO): PokedexEntity {
        return PokedexEntity(
                id = dto.id,
                name = Klaxon().toJsonString(dto.name),
                base = Klaxon().toJsonString(dto.base),
                type = Klaxon().toJsonString(dto.type)
        )
    }

    companion object {
        val log = LoggerFactory.getLogger(PokedexRepositoryImpl::class.java)
    }
}
