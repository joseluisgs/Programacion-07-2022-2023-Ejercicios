package PokemonBattle.repositories

import PokemonBattle.models.Team
import PokemonBattle.storages.IStorageWritter
import mu.KotlinLogging

class RepositoryTeamWritter(private val storageList: IStorageWritter<Team>) : IRepositoryWritter<Team> {

    private val logger = KotlinLogging.logger {}

    private val repository = mutableListOf<Team>()

    override fun saveFile() {
        logger.debug { "Repository: Escribiendo en fichero " }
        storageList.saveFile(repository.toList())
    }

    fun saveItem(item: Team) {
        logger.debug { "Repository: Añadiendo item" }
        repository.add(item)
    }
}
