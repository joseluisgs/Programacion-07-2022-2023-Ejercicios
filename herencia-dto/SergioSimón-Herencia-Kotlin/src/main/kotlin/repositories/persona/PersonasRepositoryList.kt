package repositories.persona

import models.Alumno
import models.Persona
import models.Profesor
import service.storage.persona.PersonaStorageService

class PersonasRepositoryList(
    private val storageService: PersonaStorageService
): PersonaRepository {

    private val almacen = mutableListOf<Persona>()

    override fun findAll(): List<Persona> {
        loadAll()
        return almacen
    }

    private fun loadAll() {
        almacen.clear()
        storageService.loadAll().forEach {
            almacen.add(it)
        }
    }

    override fun save(entity: Persona): Persona {
        almacen.add(entity)
        saveAll()
        return entity
    }

    private fun saveAll() {
        storageService.saveAll(almacen)
    }

    override fun alumnoJoven(): Alumno? {
        val alumonos = almacen.filterIsInstance<Alumno>()
        return alumonos.minByOrNull { it.edad }
    }

    override fun mediaAlumnoEdad(): Int {
        val alumnos = almacen.filterIsInstance<Alumno>()
        return alumnos.sumOf { it.edad } / almacen.count()
    }

    override fun groupByTipo(): List<Persona> {
        return almacen.sortedBy {
            when(it){
                is Alumno -> 1
                is Profesor -> 0
                else -> 2
            }
        }
    }

    override fun mediaNombreLogitud(): Double {
        val nombres = almacen.map { it.name.length }
        return nombres.average()
    }
}