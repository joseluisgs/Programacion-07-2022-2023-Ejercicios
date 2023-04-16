package Ficheros.BurguerPig.models.factories

import Ficheros.BurguerPig.models.Burguer
import Ficheros.BurguerPig.repositories.RepositoryIngredient

class BurguerFactory {
    companion object {
        fun create(): List<Burguer> {
            val repoIngredients = RepositoryIngredient()

            val hamburguesas = mutableListOf<Burguer>()
            var contadorBurguer = 0

            repeat(8) {
                contadorBurguer++

                val numIngredientes = (2..4).random()
                val ingredientesAleatorios = repoIngredients.getAllIngredient().shuffled().take(numIngredientes)
                val nombre = "Burguer_$contadorBurguer"
                hamburguesas.add(Burguer(nombre, ingredientesAleatorios))
            }
            return hamburguesas
        }
    }
}