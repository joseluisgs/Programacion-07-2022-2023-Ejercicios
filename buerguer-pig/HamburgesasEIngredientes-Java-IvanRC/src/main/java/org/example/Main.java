package org.example;

import config.ConfigApp;
import controller.HamburgesaController;
import controller.IngredienteController;
import factory.HamburgesaFactory;
import factory.IngredienteFactory;
import models.Hamburgesa;
import models.Ingrediente;
import repository.hamburgesa.HamburgesaRepositoryImpl;
import repository.ingrediente.IngredienteRepository;
import repository.ingrediente.IngredienteRepositoryImpl;
import storage.hamburgesa.HamburgesaStorageServiceJSON;
import storage.ingrediente.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {

        ConfigApp configApp = ConfigApp.getInstance();

        IngredienteRepository ingredienteRepository = new IngredienteRepositoryImpl(new IngredienteStorageServiceTxt());
        IngredienteController ingredienteController = new IngredienteController(ingredienteRepository);
        HamburgesaController hamburgesaController = new HamburgesaController(new HamburgesaRepositoryImpl(new HamburgesaStorageServiceJSON()), ingredienteRepository);

        List<Ingrediente> ingredientesRandom = IngredienteFactory.getInstance().getSome();
        ingredientesRandom.forEach(ingrediente ->
            {
                try {
                    ingredienteController.add(ingrediente);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        );
        List<Ingrediente> ingredientes = ingredienteController.getAll();
        ingredientes.forEach(System.out::println);
        System.out.println();

        for(int i = 0; i<=4; i++){
            hamburgesaController.add(HamburgesaFactory.getInstance().getRandom(ingredientes));
        }
        List<Hamburgesa> hamburgesas = hamburgesaController.getAll();
        hamburgesas.forEach(System.out::println);

        System.out.println("Empezamos con las consultas:");
        System.out.println();

        System.out.println("La haburgesa más cara:");
        Hamburgesa hamburgesa1 = hamburgesas.stream().max(Comparator.comparingDouble(Hamburgesa::getPrecio)).orElseGet(null);
        System.out.println(hamburgesa1);
        System.out.println();

        System.out.println("La haburgesa con más ingredientes:");
        Hamburgesa hamburgesa2 = hamburgesas.stream().max(Comparator.comparingInt(Hamburgesa::getNumberOfIngredientes)).orElseGet(null);
        System.out.println(hamburgesa2);
        System.out.println();

        System.out.println("Numero de hamburgesas por ingrediente:");
        Map<Object, Long> mapa1 = ingredientes.stream().collect(Collectors.groupingBy(i -> i.getNombre(),
                Collectors.mapping(i -> hamburgesas.stream().filter(h -> h.getIngredientes().contains(i)), Collectors.counting())
                ));
        mapa1.keySet().forEach(k -> System.out.println(k+"--"+mapa1.get(k)));
        System.out.println();

        System.out.println("Hamburgesas agrupadas por total de ingredientes:");
        System.out.println("Primera opcion:");
        Map<List<Ingrediente>, List<Hamburgesa>> mapa2 = hamburgesas.stream().collect(Collectors.groupingBy(Hamburgesa::getIngredientes));
        mapa2.keySet().forEach(k -> System.out.println(k+":\n"+mapa2.get(k)+"\n"));
        System.out.println();
        System.out.println("Segunda opción:");
        Map<Double, List<Hamburgesa>> mapa3 = hamburgesas.stream().collect(Collectors.groupingBy(Hamburgesa::getPrecio));
        mapa3.keySet().forEach(k -> System.out.println(k+"--"+mapa3.get(k)));
        System.out.println();

        System.out.println("Precio medio de las hamburgesas:");
        Double decimal1 = hamburgesas.stream().collect(Collectors.averagingDouble(Hamburgesa::getPrecio));
        System.out.println(decimal1);
        System.out.println();

        System.out.println("Precio medio de los ingredientes:");
        Double decimal2 = ingredientes.stream().collect(Collectors.averagingDouble(Ingrediente::getPrecio));
        System.out.println(decimal2);
        System.out.println();
    }
}