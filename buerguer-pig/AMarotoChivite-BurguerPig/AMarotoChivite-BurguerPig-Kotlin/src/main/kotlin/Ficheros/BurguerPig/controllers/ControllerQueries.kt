package Ficheros.BurguerPig.controllers

import Ficheros.BurguerPig.models.Burguer
import Ficheros.BurguerPig.repositories.RepositoryIngredient

class ControllerQueries(private val burguerListOfStorageReadFile: List<Burguer>) {

    private val repoIngredients = RepositoryIngredient()

    private val groupByIngredient = repoIngredients.getAllIngredient().groupBy { it.name }

    fun burguerMoreExpensive(): Burguer {
        return burguerListOfStorageReadFile.maxBy { it.getPrice() }
    }

    fun burguerMoreIngredients(): Burguer {
        return burguerListOfStorageReadFile.maxBy { it.ingredients.size }
    }

    fun numBurguerByIngredient(): Map<String, Int> {
        return groupByIngredient.mapValues {
            burguerListOfStorageReadFile.count { burguer ->
                burguer.ingredients.any { ingredient -> ingredient?.name == it.key }
            }
        }
    }

    fun burguersByIngredient(): Map<String, List<Burguer>> {
        return groupByIngredient.mapValues { ingredient ->
            burguerListOfStorageReadFile.filter { burguer ->
                burguer.ingredients.any { it?.name == ingredient.key }
            }
        }
    }

    fun averagePriceBurguers(): Double {
        return burguerListOfStorageReadFile.map { it.getPrice() }.average()
    }

    fun averagePriceIngredients(): Double {
        return repoIngredients.getAllIngredient().map { it.price }.average()
    }

}