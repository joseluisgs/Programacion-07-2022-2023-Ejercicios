package repositories

import models.Hamburguesa
import java.util.UUID

interface IHamburguesaRepository {
    fun crearHamburguesa(hamburguesa: Hamburguesa): Hamburguesa?
    fun listarHamburguesas(): List<Hamburguesa>
    fun listarHamburguesaPorID(id: UUID): Hamburguesa?
    fun guardarTodo()
    fun cargarTodo()
}