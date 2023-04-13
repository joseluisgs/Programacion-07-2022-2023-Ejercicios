package controllers

import exceptions.HamburguesaNoEncontradaException
import exceptions.HamburguesaYaExisteException
import models.Hamburguesa
import repositories.IHamburguesaRepository
import java.util.*

class HamburguesaController(private val repository: IHamburguesaRepository) {
    fun crearHamburguesa(hamburguesa: Hamburguesa) =
        repository.crearHamburguesa(hamburguesa)
            ?: throw HamburguesaYaExisteException("La hamburguesa ya existe")

    fun listarHamburguesaPorID(id: UUID) =
        repository.listarHamburguesaPorID(id)
            ?: throw HamburguesaNoEncontradaException("No se encontró la hamburguesa")

    fun listarHamburguesas() = repository.listarHamburguesas()
    fun guardarTodo() = repository.guardarTodo()
    fun cargarTodo() = repository.cargarTodo()

}