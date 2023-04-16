package Ficheros.BurguerPig.models.factories;

import Ficheros.BurguerPig.models.Burguer;
import Ficheros.BurguerPig.models.Ingredient;
import Ficheros.BurguerPig.repositories.RepositoryIngredient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class BurguerFactory {
    public static List<Burguer> create() {
        RepositoryIngredient repoIngredients = new RepositoryIngredient();

        List<Burguer> hamburguesas = new ArrayList<>();
        int contadorBurguer = 0;

        for (int i = 0; i < 8; i++) {
            contadorBurguer++;

            int numIngredientes = new Random().nextInt(3) + 2;
            List<Ingredient> ingredientesAleatorios = repoIngredients.getAllIngredient().stream()
                    .collect(Collectors.toList());
            Collections.shuffle(ingredientesAleatorios);
            ingredientesAleatorios = ingredientesAleatorios.subList(0, numIngredientes);
            String nombre = "Burguer_" + contadorBurguer;
            hamburguesas.add(new Burguer(nombre, ingredientesAleatorios));
        }
        return hamburguesas;
    }
}


