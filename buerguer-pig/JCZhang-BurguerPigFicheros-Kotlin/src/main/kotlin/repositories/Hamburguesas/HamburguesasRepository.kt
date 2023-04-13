package repositories.Hamburguesas

import models.Hamburguesa
import models.Ingrediente
import mu.KotlinLogging
import services.storage.base.StorageService

class HamburguesasRepository(private val storageService: StorageService<Hamburguesa>){

    private val logger = KotlinLogging.logger {}

    private val tomate = Ingrediente(nombre = "Tomate", precio = 0.75)
    private val lechuga = Ingrediente(nombre = "Lechuga", precio = 0.50)
    private val carne = Ingrediente(nombre = "Carne", precio = 2.50)
    private val pepinillos = Ingrediente(nombre = "Pepinillos", precio = 0.35)
    private val bacon = Ingrediente(nombre = "Bacon", precio = 1.25)
    private val queso = Ingrediente(nombre = "Queso", precio = 1.00)

    private val cheeseBurger = Hamburguesa(nombre = "CheeseBurger", ingredientes = listOf(carne, bacon, queso))
    private val baconCrispyBurger =
        Hamburguesa(nombre = "BaconCrispyBurger", ingredientes = listOf(carne, bacon, queso, lechuga, pepinillos))
    private val allInBurger =
        Hamburguesa(nombre = "AllInBurger", ingredientes = listOf(carne, tomate, lechuga, pepinillos, bacon, queso))
    private val doubleCheeseBurger =
        Hamburguesa(nombre = "DoubleCheeseBurger", ingredientes = listOf(carne, carne, bacon, queso, queso))

    private val hamburguesas = listOf(cheeseBurger, baconCrispyBurger, allInBurger, doubleCheeseBurger)


    fun save() {
        logger.debug("Repository: Iniciada la función de guardar las hamburguesas en el fichero de texto")
        storageService.saveAll(hamburguesas)
    }

    fun load() {
        logger.debug("Repository: Iniciada la función de cargar las hamburguesas desde el fichero de texto")
        storageService.loadAll()
    }


}