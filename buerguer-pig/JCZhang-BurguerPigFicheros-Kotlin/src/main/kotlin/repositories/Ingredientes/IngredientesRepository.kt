package repositories.Ingredientes

import models.Ingrediente
import mu.KotlinLogging
import services.storage.base.StorageService

class IngredientesRepository(private val storageService: StorageService<Ingrediente>) {

    private val logger = KotlinLogging.logger {}

    private val tomate = Ingrediente(nombre = "Tomate", precio = 0.75)
    private val lechuga = Ingrediente(nombre = "Lechuga", precio = 0.50)
    private val carne = Ingrediente(nombre = "Carne", precio = 2.50)
    private val pepinillos = Ingrediente(nombre = "Pepinillos", precio = 0.35)
    private val bacon = Ingrediente(nombre = "Bacon", precio = 1.25)
    private val queso = Ingrediente(nombre = "Queso", precio = 1.00)

    private val ingredientes = listOf(tomate, lechuga, carne, pepinillos, bacon, queso)


    fun save(){
        logger.debug("Ingredientes Repository: Iniciando función save()")
        storageService.saveAll(ingredientes)
    }

    fun load(){
        logger.debug("Ingredientes Repository: Iniciando función load()")
        storageService.loadAll()
    }
}