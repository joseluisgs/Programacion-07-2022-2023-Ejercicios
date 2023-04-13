package Interfaz

import Models.Hamburguesa
import java.util.*

interface HamburguesaRepository {
    fun crearHamburguesa(hamburguesa: Hamburguesa)
    fun obtenerHamburguesaPorId(id: UUID): Hamburguesa?
    fun obtenerHamburguesaMasCara(): Hamburguesa?
    fun obtenerHamburguesaConMasIngredientes(): Hamburguesa?
    fun obtenerNumeroDeHamburguesasPorIngrediente(): Map<String, Int>
    fun obtenerHamburguesasAgrupadasPorTotalDeIngredientes(): Map<Int, List<Hamburguesa>>
    fun obtenerPrecioPromedioDeHamburguesas(): Double
    fun obtenerPrecioPromedioDeIngredientes(): Double
}