package Ficheros.BurguerPig.controllers;

import Ficheros.BurguerPig.models.Burguer;
import Ficheros.BurguerPig.models.Ingredient;
import Ficheros.BurguerPig.repositories.RepositoryIngredient;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ControllerQueries {

    private final List<Burguer> burguerListOfStorageReadFile;
    private final RepositoryIngredient repoIngredients;

    private final Map<String, List<Ingredient>> groupByIngredient;

    public ControllerQueries(List<Burguer> burguerListOfStorageReadFile) {
        this.burguerListOfStorageReadFile = burguerListOfStorageReadFile;
        this.repoIngredients = new RepositoryIngredient();

        groupByIngredient = repoIngredients.getAllIngredient().stream()
                .collect(Collectors.groupingBy(Ingredient::getName));
    }

    public Burguer burguerMoreExpensive() {
        return burguerListOfStorageReadFile.stream().max(Comparator.comparing(Burguer::getPrice)).orElse(null);
    }

    public Burguer burguerMoreIngredients() {
        return burguerListOfStorageReadFile.stream().max(Comparator.comparingInt(b -> b.getIngredients().size())).orElse(null);
    }

    public Map<String, Integer> numBurguerByIngredient() {
        Map<String, Integer> result = new HashMap<>();
        groupByIngredient.forEach((ingredientName, ingredientList) -> {
            int count = 0;
            for (Burguer burguer : burguerListOfStorageReadFile) {
                if (burguer.getIngredients().stream().anyMatch(ingredient -> ingredient != null && ingredient.getName().equals(ingredientName))) {
                    count++;
                }
            }
            result.put(ingredientName, count);
        });
        return result;
    }

    public Map<String, List<Burguer>> burguersByIngredient() {
        Map<String, List<Burguer>> result = new HashMap<>();
        groupByIngredient.forEach((ingredientName, ingredientList) -> {
            List<Burguer> burguers = burguerListOfStorageReadFile.stream()
                    .filter(burguer -> burguer.getIngredients().stream()
                            .anyMatch(ingredient -> ingredient != null && ingredient.getName().equals(ingredientName)))
                    .collect(Collectors.toList());
            result.put(ingredientName, burguers);
        });
        return result;
    }

    public double averagePriceBurguers() {
        return burguerListOfStorageReadFile.stream().mapToDouble(Burguer::getPrice).average().orElse(0.0);
    }

    public double averagePriceIngredients() {
        return repoIngredients.getAllIngredient().stream().mapToDouble(Ingredient::getPrice).average().orElse(0.0);
    }
}
