package repository.Hamburger

import model.Hamburgesa
import mu.KotlinLogging
import storageService.Hamburger.HamburgesaStorageService
import java.util.*

class HamburgesaRepositoryImpl(
    private val storage: HamburgesaStorageService
): HamburgesaRepository {

    private val logger = KotlinLogging.logger {}
    private var hamburgesas = mutableListOf<Hamburgesa>()

    override fun findByName(name: String): List<Hamburgesa> {
        logger.debug { "Se buscan hamburgesas según su nombre." }
        TODO("Not yet implemented")
    }

    override fun findById(id: UUID): Hamburgesa? {
        logger.debug { "Se busca una hamburgesa según su id." }
        loadAll()
        return hamburgesas.find { it.id == id }
    }

    override fun getAll(): List<Hamburgesa> {
        logger.debug { "Se consiguen hamburgesas." }
        loadAll()
        return hamburgesas.toList()
    }

    override fun add(entity: Hamburgesa): Hamburgesa? {
        logger.debug { "Se añade una hamburgesa." }
        (findById(entity.id))?:run{
            hamburgesas.add(entity)
            saveAll()
            return entity
        }
        return null
    }

    override fun delete(id: UUID): Hamburgesa? {
        logger.debug { "Se elimina una hamburgesa." }
        TODO("Not yet implemented")
    }

    override fun update(entity: Hamburgesa): Hamburgesa? {
        logger.debug { "Se actualiza una hamburgesa." }
        TODO("Not yet implemented")
    }

    fun saveAll(){
        logger.debug { "Se guargan las hamburgesas en el storage." }
        storage.saveAll(hamburgesas)
    }

    fun loadAll(){
        logger.debug { "Se cargan las hamburgesas en el storage." }
        hamburgesas = storage.loadAll().toMutableList()
    }
}