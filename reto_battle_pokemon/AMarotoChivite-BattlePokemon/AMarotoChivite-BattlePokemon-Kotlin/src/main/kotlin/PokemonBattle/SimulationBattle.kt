package PokemonBattle

import PokemonBattle.models.Pokemon
import PokemonBattle.models.Trainer
import kotlin.system.exitProcess

fun initBattle(listTrainers: MutableList<Trainer>) {
    val trainerPlayer = listTrainers[0]
    val trainerEnemy = listTrainers[1]
    var countRounds = 0

    // En primer lugar, empieza la batalla eligiendo pokemon para pelear
    while (true) {
        if (chooseFirstPokemonToFight(trainerPlayer)) {
            chooseFirstPokemonToFightRandom(trainerEnemy)
            break
        }
    }

    while (true) {
        countRounds++
        println("=== RONDA $countRounds ===")
        val pokemonPlayerInBattle = trainerPlayer.getPokemonInBattle()
        val pokemonEnemyInBattle = trainerEnemy.getPokemonInBattle()

        println("Tu Pokemon:")
        println(pokemonPlayerInBattle!!.printOnlyStats())
        println()

        println("Pokemon Rival:")
        println(pokemonEnemyInBattle!!.printOnlyStats())
        println()

        // Turno del jugador
        println("Ataca Jugador...")
        val movementToAttack = trainerPlayer.chooseMovement(pokemonPlayerInBattle)
        pokemonPlayerInBattle.attack(pokemonEnemyInBattle, movementToAttack)

        // Verificamos si alguien ha ganado
        checkWinner(trainerPlayer, trainerEnemy, countRounds)

        // Verificamos si hemos debilitado al pokemon enemigo
        if (checkDeadPokemonRival(pokemonEnemyInBattle)) {
            // El enemigo saca un pokemon vivo aleatorio
            choosePokemonToFightRandom(trainerEnemy)
        }

        // Turno Enemigo Aleatorio
        println()
        println("Ataca Enemigo...")
        val movementToAttackEnemy = trainerEnemy.chooseMovementRandom(pokemonEnemyInBattle)
        pokemonEnemyInBattle.attack(pokemonPlayerInBattle, movementToAttackEnemy)

        // Verificamos si alguien ha ganado
        checkWinner(trainerPlayer, trainerEnemy, countRounds)

        // Verificamos si ha matado a nuestro pokemon
        if (checkDeadPokemonAllie(pokemonPlayerInBattle)) {
            // Tenemos que elegir otro pokemon para pelear
            choosePokemonToFight(trainerPlayer)
        }

        // Si continuamos jugando damos la opción de cambiar de pokemon al jugador
        if (wantChangePokemon()) {
            choosePokemonToFight(trainerPlayer)
        }
    }
}

/*
La información de los Pokémon de cada entrenador (PS y PP restantes).
 */
fun finalInform(trainer1: Trainer, trainer2: Trainer) {
    trainer1.savePokemon()
    trainer2.savePokemon()
    println("=== EQUIPO Jugador ===")
    println(trainer1.getTeamInReserva().joinToString("\n"))
    println()

    println("=== EQUIPO Enemigo ===")
    println(trainer2.getTeamInReserva().joinToString("\n"))
    println()
}

fun wantChangePokemon(): Boolean {
    while (true) {
        println("Deseas cambiar de pokemon? (Y/N):")
        val decision = readln()
        if (decision == "Y" || decision == "y") {
            return true
        }
        if (decision == "N" || decision == "n") {
            return false
        }
    }
}

fun checkWinner(trainer1: Trainer, trainer2: Trainer, countRounds: Int) {
    if (checkDeadsAllPokemon(trainer1.getTeamInReserva(), trainer2.getTeamInReserva()) == 1) {
        println()
        println("=== INFORME ===")
        println("Ha ganado el enemigo en $countRounds turnos!")
        finalInform(trainer1, trainer2)
        exitProcess(0)
    }
    if (checkDeadsAllPokemon(trainer1.getTeamInReserva(), trainer2.getTeamInReserva()) == 2) {
        println()
        println("=== INFORME ===")
        println("Has ganado en $countRounds turnos!")
        finalInform(trainer1, trainer2)
        exitProcess(0)
    }
}

fun checkDeadsAllPokemon(trainer1Pokemons: List<Pokemon>, trainer2Pokemons: List<Pokemon>): Int {

    // Verifica si todos los Pokemon de mi equipo están muertos
    if (trainer1Pokemons.all { it.life <= 0 }) {
        // Ha ganado el enemigo
        return 1
    }
    // Verifica si todos los Pokemon del equipo enemigo están muertos
    if (trainer2Pokemons.all { it.life <= 0 }) {
        // Hemos ganado
        return 2
    }

    // Nadie ha ganado
    return 0
}

fun checkDeadPokemonAllie(pokemonAllieInBattle: Pokemon): Boolean {
    if (pokemonAllieInBattle.life < 0) {
        println("Pokemon aliado debilitado!")
        return true
    } else {
        return false
    }
}

fun checkDeadPokemonRival(pokemonEnemyInBattle: Pokemon): Boolean {
    if (pokemonEnemyInBattle.life < 0) {
        println("Pokemon enemigo debilitado!")
        return true
    } else {
        return false
    }
}

fun choosePokemonToFightRandom(trainer: Trainer) {

    while (true) {
        /* // Verifica si todos los Pokemon del equipo del enemigo están muertos
         if (trainer.getTeamInReserva().all { it.life <= 0 }) {
             break
         }
 */
        val decision = (0..4).random()
        if (trainer.takeOutPokemonOfTheBattleRandom(decision)) {
            break
        }
    }
}

fun choosePokemonToFight(trainer: Trainer): Boolean {
    while (true) {
        println("Elige el pokemon que desees sacar (1,2,3,4 ó 5): ")
        println(trainer.getTeamInReserva().joinToString("\n"))
        val decision = readln().toIntOrNull() ?: 0
        if (decision in 1..5) {
            if (trainer.takeOutPokemonOfTheBattle(decision - 1)) {
                return true
            }
        }
    }
}

fun chooseFirstPokemonToFightRandom(trainer: Trainer) {
    val decision = (0..5).random()
    trainer.takeOutFirstPokemon(decision)
}

fun chooseFirstPokemonToFight(trainer: Trainer): Boolean {
    while (true) {
        println("Elige el pokemon que desees sacar (1,2,3,4,5 ó 6): ")
        println(trainer.getTeamInReserva().joinToString("\n"))
        val decision = readln().toIntOrNull() ?: 0
        if (decision in 1..6) {
            trainer.takeOutFirstPokemon(decision - 1)
            return true
        }
    }
}
