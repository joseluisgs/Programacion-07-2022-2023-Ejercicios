package repository.hamburguesa

import models.Hamburguesa
import repository.CrudRepository

interface HamburguesaRepository: CrudRepository<Hamburguesa, Int> {
    fun getAllOrderByPrecio(): List<Hamburguesa>
    fun findByNombre(nombre: String): Hamburguesa?

    //Requisitos
    fun getHamburguesaMasCara(): Hamburguesa?
    fun getHamburguesaConMasIngredientes(): Hamburguesa?
    fun getPrecioMedio(): Double
    fun getPrecioMedioIngredientes(): Map<String,Double>
}