package PokemonBattle.utils

import PokemonBattle.mappers.PokemonListMapper
import PokemonBattle.models.Pokemon
import PokemonBattle.models.Team
import PokemonBattle.models.Trainer

fun decisionTeam(listTeams: List<Team>): MutableList<Trainer> {
    // Transformamos de DTO a Modelos
    val team1 = PokemonListMapper().toModelList(listTeams[0])
    val team2 = PokemonListMapper().toModelList(listTeams[1])

    while (true) {
        printModels(team1, team2)
        println()
        println(" -> Jugador 1 elige tu equipo (1 o 2): ")
        val respuesta = readln().toIntOrNull() ?: 0
        val listTrainers = mutableListOf<Trainer>()
        if (respuesta == 1) {
            listTrainers.add(Trainer(team1)) // Primera posición de la lista es el JUGADOR 1
            listTrainers.add(Trainer(team2))
            return listTrainers
        }
        if (respuesta == 2) {
            listTrainers.add(Trainer(team2))
            listTrainers.add(Trainer(team1))
            return listTrainers
        }
    }
}

fun printModels(team1: List<Pokemon>, team2: List<Pokemon>) {
    println(" === EQUIPO 1 === ")
    println(team1.joinToString("\n"))
    println()
    println(" === EQUIPO 2 === ")
    println(team2.joinToString("\n"))
}
