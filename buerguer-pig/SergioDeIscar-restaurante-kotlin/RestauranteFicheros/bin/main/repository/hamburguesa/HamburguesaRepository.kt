package repository.hamburguesa

import models.Hamburguesa
import repository.CrudRepository

interface HamburguesaRepository: CrudRepository<Hamburguesa, Int> {
    fun getAllOrderByPrecio(): List<Hamburguesa>
    fun findByNombre(nombre: String): Hamburguesa?
}