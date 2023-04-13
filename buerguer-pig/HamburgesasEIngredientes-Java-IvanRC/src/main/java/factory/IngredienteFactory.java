package factory;

import models.Ingrediente;

import java.util.ArrayList;

public class IngredienteFactory {
    private IngredienteFactory() {}
    private static IngredienteFactory instance = null;

    public static IngredienteFactory getInstance() {
        if(instance == null) instance = new IngredienteFactory();
        return instance;
    }

    private static int contador = 0;

    private static int nextId(){
        contador++;
        return contador;
    }

    public ArrayList<Ingrediente> getSome(){
        ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
        int number = (int)(Math.random()*5+1);
        String[] nombres = {"Salsa", "Carne", "Lechuga", "Cebolla", "Pepinillo", "Pan"};
        for(int i = 1; i<=number; i++){
            double precio = (Math.random()*100.0);
            ingredientes.add(
                    new Ingrediente(nextId(), nombres[(int)(Math.random()* nombres.length)], precio)
            );
        }
        return ingredientes;
    }
}
