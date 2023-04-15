package repository.Ingrediente

import model.Ingrediente
import mu.KotlinLogging
import storageService.base.StorageService

class IngredienteRepositoryImpl(
    private val storage: StorageService<Ingrediente>
): IngredienteRepository {

    private val logger = KotlinLogging.logger {}
    private var ingredientes = mutableListOf<Ingrediente>()

    override fun findByName(name: String): List<Ingrediente> {
        logger.debug { "Repository: Se buscan ingredientes según su nombre." }
        TODO("Not yet implemented")
    }

    override fun findById(id: Int): Ingrediente? {
        logger.debug { "Repository: Se busca un ingrediente según su id." }
        loadAll()
        return ingredientes.find { it.id == id }
    }

    override fun getAll(): List<Ingrediente> {
        logger.debug { "Repository: Se consiguen ingredientes." }
        loadAll()
        return ingredientes.toList()
    }

    override fun add(entity: Ingrediente): Ingrediente? {
        logger.debug { "Repository: Se añade un ingrediente." }
        (findById(entity.id))?:run{
            ingredientes.add(entity)
            saveAll()
            return entity
        }
        return null
    }

    override fun delete(id: Int): Ingrediente? {
        logger.debug { "Repository: Se elimina un ingrediente." }
        TODO("Not yet implemented")
    }

    override fun update(entity: Ingrediente): Ingrediente? {
        logger.debug { "Repository: Se actualiza un ingrediente." }
        TODO("Not yet implemented")
    }

    fun saveAll(){
        logger.debug { "Repository: Se guargan los ingredientes en el storage." }
        storage.saveAll(ingredientes)
    }

    fun loadAll(){
        logger.debug { "Repository: Se cargan los ingredientes en el storage." }
        ingredientes = storage.loadAll().toMutableList()
    }
}