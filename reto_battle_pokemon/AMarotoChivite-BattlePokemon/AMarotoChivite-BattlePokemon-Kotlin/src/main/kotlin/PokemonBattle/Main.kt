package PokemonBattle

import PokemonBattle.config.ConfigApp
import PokemonBattle.controllers.ControllerBuilder
import PokemonBattle.controllers.ControllerWritter
import PokemonBattle.controllers.ControllerWritterReader
import PokemonBattle.models.Team
import PokemonBattle.repositories.RepositoryTeamWritter
import PokemonBattle.repositories.RepositoryTeamWritterReader
import PokemonBattle.storages.StorageCSV
import PokemonBattle.storages.StorageJSON
import PokemonBattle.utils.decisionTeam

@ExperimentalStdlibApi // Tenemos que propagar la anotación
fun main() {
    // Cargamos la configuración general de los ficheros
    ConfigApp.init()

    // Creador del contenido que manipularemos
    val controllerBuilder = ControllerBuilder()
    val team1: Team = controllerBuilder.createTeam()
    val team2: Team = controllerBuilder.createTeam()

    // Exportamos a JSON
    val storageJSONtoWriteRead = StorageJSON()
    val repositoryJSON = RepositoryTeamWritterReader(storageJSONtoWriteRead)
    val controllerWritterReaderJSON = ControllerWritterReader(repositoryJSON)
    // Añadimos los equipos a su repositorio
    repositoryJSON.saveItem(team1)
    repositoryJSON.saveItem(team2)
    controllerWritterReaderJSON.saveFile()
    // Verificamos mediante el controlador si tenemos los 6 pokemon en cada equipo
    val listTeams = controllerWritterReaderJSON.readFile()

    // Exportamos a CSV
    val storageCSVtoWrite = StorageCSV()
    val repositoryCSV = RepositoryTeamWritter(storageCSVtoWrite)
    val controllerWritterReaderCSV = ControllerWritter(repositoryCSV)
    // Añadimos los equipos a su repositorio
    repositoryCSV.saveItem(team1)
    repositoryCSV.saveItem(team2)
    controllerWritterReaderCSV.saveFile()

    // Anteriormente, leímos desde JSON y empezamos a preparar la batalla
    val listTrainers = decisionTeam(listTeams)
    initBattle(listTrainers)
}



