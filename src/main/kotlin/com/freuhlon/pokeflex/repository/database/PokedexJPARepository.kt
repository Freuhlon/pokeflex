package com.freuhlon.pokeflex.repository.database

import com.freuhlon.pokeflex.repository.database.entity.PokedexEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PokedexJPARepository: JpaRepository<PokedexEntity, Long>  {
}
