package PokemonBattle.models

import PokemonBattle.enums.Types

data class Pokemon(
    val id: Int,
    val ratioDefense: Double,
    var life: Int,
    val listType: List<Types>,
    val listMovement: List<Movement>
) {
    fun attack(pokemonDefensor: Pokemon, movementToAttack: Movement) {

        val listTypeDefensor = pokemonDefensor.listType
        val typeMovement = movementToAttack.type
        val damageBase = movementToAttack.power

        // Primero verificamos si el movimiento es del mismo elemento que el pokemon
        var extraDamageSameTypeMovement = 0
        if (listTypeDefensor.any { it == typeMovement }) {
            extraDamageSameTypeMovement = (damageBase * 0.5).toInt()
        }

        // Calculamos las efectividades
        var damageCalculateEffectiveness = damageBase
        listTypeDefensor.forEach { typeDefensor ->
            val quantityEffectiveness = typeMovement.effectiveness[typeDefensor]
            if (quantityEffectiveness != null) {
                damageCalculateEffectiveness = (damageCalculateEffectiveness * quantityEffectiveness).toInt()
            }
        }


        // Para tener un control en pantalla de las eficacias
        if (damageCalculateEffectiveness > damageBase) {
            println("Ha sido efectivo!")
        } else if (damageCalculateEffectiveness < damageBase) {
            println("No ha sido efectivo!")
        } else {
            println("Ha sido un ataque sin eficacias!")
        }

        // Cálculo del daño final
        val finalDamage = damageCalculateEffectiveness + extraDamageSameTypeMovement
        val damageToApply = (finalDamage - (finalDamage * pokemonDefensor.ratioDefense)).toInt()
        pokemonDefensor.life = pokemonDefensor.life - damageToApply

        println("Se ha hecho un daño de: $damageToApply de tipo ${typeMovement.emoji}")
    }

    fun printOnlyStats(): String {
        return "Pokemon(ratioDefense='$ratioDefense', maxLife='$life', listType=${printTypes()})"
    }

    override fun toString(): String {
        return "Pokemon(ratioDefense='$ratioDefense', maxLife='$life', listType=${printTypes()} ${printMovement()})"
    }

    private fun printTypes(): String {
        return listType.joinToString(separator = "+") { type -> type.emoji }
    }

    private fun printMovement(): String {
        return "ListMovement: ${listMovement.joinToString()})"
    }
}