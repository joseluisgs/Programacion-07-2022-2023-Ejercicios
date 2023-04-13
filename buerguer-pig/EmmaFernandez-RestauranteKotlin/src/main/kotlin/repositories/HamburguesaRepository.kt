package repositories

import models.Hamburguesa
import services.storage.HamburguesaStorageService
import java.util.*

class HamburguesaRepository(private val hamburguesas: HamburguesaStorageService) : IHamburguesaRepository {
    private val hamburguesasList = mutableListOf<Hamburguesa>()

    override fun crearHamburguesa(hamburguesa: Hamburguesa): Hamburguesa? {
        return if (!hamburguesasList.contains(hamburguesa)) {
            hamburguesasList.add(hamburguesa)
            hamburguesa
        } else null
    }

    override fun listarHamburguesas(): List<Hamburguesa> {
        return hamburguesasList.toList()
    }

    override fun listarHamburguesaPorID(id: UUID): Hamburguesa? {
        return hamburguesasList.find { it.id == id }
    }

    override fun guardarTodo() {
        hamburguesas.saveAll(hamburguesasList)
    }

    override fun cargarTodo() {
        hamburguesasList.addAll(hamburguesas.loadAll())
    }
}