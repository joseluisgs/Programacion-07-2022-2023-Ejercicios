package factory;

import models.Hamburgesa;
import models.Ingrediente;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HamburgesaFactory {

    private HamburgesaFactory() {}
    private static HamburgesaFactory instance = null;

    public static HamburgesaFactory getInstance() {
        if(instance == null) instance = new HamburgesaFactory();
        return instance;
    }

    public Hamburgesa getRandom(List<Ingrediente> ingredientes){
        String[] nombres = {"Hambuergesa de vaca", "Hambuergesa de pollo", "Hambuergesa de cerdo"};
        List<Ingrediente> ingredientesHamburgesa = new ArrayList<>();
        int number = (int)(Math.random()*7+1);
        for(int i = 1; i<=number; i++){
            double precio = (Math.random()*100.0);
            ingredientesHamburgesa.add(
                    ingredientes.get((int) (Math.random() * ingredientes.size()))
            );
        }
        return new Hamburgesa(UUID.randomUUID(), nombres[(int)(Math.random()*nombres.length)], ingredientesHamburgesa);
    }
}
