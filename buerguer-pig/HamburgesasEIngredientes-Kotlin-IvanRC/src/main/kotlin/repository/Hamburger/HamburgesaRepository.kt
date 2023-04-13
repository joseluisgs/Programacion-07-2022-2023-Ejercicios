package repository.Hamburger

import model.Hamburgesa
import repository.base.CrudRepository
import java.util.UUID

interface HamburgesaRepository: CrudRepository<Hamburgesa, UUID> {
    fun findByName(name: String): List<Hamburgesa>
}