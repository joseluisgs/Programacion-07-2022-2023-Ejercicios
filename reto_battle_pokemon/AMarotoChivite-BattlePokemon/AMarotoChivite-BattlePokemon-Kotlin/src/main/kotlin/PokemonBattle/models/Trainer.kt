package PokemonBattle.models

data class Trainer(private val equipo: List<Pokemon>) {
    private var pokemonInBattle: Pokemon? = null
    private var pokemonStorage: MutableList<Pokemon> = equipo.toMutableList()

    // Solamente cuando acabamos la batalla para poder realizar el informe
    fun savePokemon() {
        pokemonStorage.add(pokemonInBattle!!)
    }

    fun getTeamInReserva(): List<Pokemon> {
        return pokemonStorage
    }

    fun getPokemonInBattle(): Pokemon? {
        return pokemonInBattle
    }

    // Caso 1. Inicio de la batalla no hay ningún pokemon fuera
    fun takeOutFirstPokemon(positionPokemonInTeam: Int) {
        if (pokemonInBattle == null) {
            pokemonInBattle = pokemonStorage[positionPokemonInTeam]
            // Sacamos el pokemon del almacén
            pokemonStorage.removeAt(positionPokemonInTeam)
        }
    }

    // Caso 2. Disponemos de pokemon en batalla y deberemos guardarlo en nuestro almacén
    fun takeOutPokemonOfTheBattle(positionPokemonInTeam: Int): Boolean {
        // Filtros
        if (pokemonStorage[positionPokemonInTeam].id == pokemonInBattle!!.id) {
            println("El pokemon seleccionado está en batalla!")
            return false
        }
        if (pokemonStorage[positionPokemonInTeam].life <= 0) {
            println("El pokemon seleccionado está debilitado!")
            return false
        }

        if (pokemonInBattle != null) {
            // Metemos al pokemon que estaba en batalla en nuestro storage
            pokemonStorage.add(pokemonInBattle!!)
            // Sacamos al nuevo
            pokemonInBattle = pokemonStorage[positionPokemonInTeam]
            // Lo sacamos del storage
            pokemonStorage.removeAt(positionPokemonInTeam)
            return true
        }
        return false
    }

    // Caso 2 (Enemigo). Únicamente sacará pokemon cuando se mueran en batalla los suyos
    fun takeOutPokemonOfTheBattleRandom(positionPokemonInTeam: Int): Boolean {
        // Filtros
        if (pokemonStorage[positionPokemonInTeam].id == pokemonInBattle!!.id) {
            return false
        }
        if (pokemonStorage[positionPokemonInTeam].life <= 0) {
            return false
        }
        if (pokemonInBattle != null) {
            // Metemos al pokemon que estaba en batalla en nuestro storage
            pokemonStorage.add(pokemonInBattle!!)
            // Sacamos al nuevo
            pokemonInBattle = pokemonStorage[positionPokemonInTeam]
            // Lo sacamos del storage
            pokemonStorage.removeAt(positionPokemonInTeam)
            return true
        }
        return false
    }

    fun chooseMovement(pokemonPlayerInBattle: Pokemon?): Movement {
        val listMovement = pokemonPlayerInBattle!!.listMovement
        while (true) {
            println("Elige el movimiento que vas a utilizar (1,2,3 ó 4):")
            println(listMovement.joinToString("\n"))
            val decision = readln().toIntOrNull() ?: 0
            if (decision in 1..4 && listMovement[decision - 1].pointsPower > 0) {
                // Quitamos PP a la habilidad
                pokemonPlayerInBattle.listMovement[decision - 1].pointsPower--
                return listMovement[decision - 1]

            } else if (decision in 1..4 && listMovement[decision - 1].pointsPower == 0) {
                println("El movimiento tiene 0 PP!")
            } else {
                println("Has introducido mal el valor!")
            }
        }
    }

    fun chooseMovementRandom(pokemonEnemyInBattle: Pokemon): Movement {
        val listMovement = pokemonEnemyInBattle.listMovement
        while (true) {
            val decision = (0..3).random()
            if (listMovement[decision].pointsPower > 0) {
                // Quitamos PP a la habilidad
                pokemonEnemyInBattle.listMovement[decision].pointsPower--
                return listMovement[decision]
            }
        }
    }
}