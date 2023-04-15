package controllers

import repositories.Hamburguesas.HamburguesasRepository
import services.storage.hamburguesas.HamburguesaFicheroTextoService.logger

class HamburguesasController(private val repository: HamburguesasRepository) {
    fun save(){
        logger.debug("Controller: Iniciando función save")
        repository.save()
    }

    fun load() {
        logger.debug("Controller: Iniciando función load")
        repository.load()
    }
}