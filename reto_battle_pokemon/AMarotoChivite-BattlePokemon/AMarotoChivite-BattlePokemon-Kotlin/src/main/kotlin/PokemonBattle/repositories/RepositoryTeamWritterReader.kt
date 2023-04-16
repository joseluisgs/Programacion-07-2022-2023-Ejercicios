package PokemonBattle.repositories

import PokemonBattle.models.Team
import PokemonBattle.storages.IStorageWritterReader
import mu.KotlinLogging

class RepositoryTeamWritterReader(private val storageList: IStorageWritterReader<Team>) :
    IRepositoryWritterReader<Team> {

    private val logger = KotlinLogging.logger {}

    private val repository = mutableListOf<Team>()

    override fun saveFile() {
        logger.debug { "Repository: Escribiendo en fichero " }
        storageList.saveFile(repository.toList())
    }

    override fun readFile(): List<Team> {
        logger.debug { "Repository: Leyendo desde fichero " }
        return storageList.readFile()
    }

    fun saveItem(item: Team) {
        logger.debug { "Repository: Añadiendo item" }
        repository.add(item)
    }
}
