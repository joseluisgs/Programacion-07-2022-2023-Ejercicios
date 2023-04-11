package validator;

import exception.ingredienteException.IngredienteException;
import models.Hamburgesa;
import models.Ingrediente;

import java.util.ArrayList;
import java.util.List;

public class Validator {

    public static boolean validateNumber(String number, Exception exception, Number min) throws Exception {
        if(min instanceof Integer){
            var regex = "-?[0-9]*";
            if(!number.matches(regex)){
                throw exception;
            }
            if(Integer.parseInt(number) < Integer.parseInt(min.toString())){
                throw exception;
            }
        }else{
            if(min instanceof Double){
                var regex = "-?[0-9]*.[0-9]*";
                if(!number.matches(regex)){
                    throw exception;
                }
                if(Double.parseDouble(number) < Double.parseDouble(min.toString())){
                    throw exception;
                }
            }
        }
        return true;
    }

    public static boolean validateString(String text, Exception exception) throws Exception {
        if(text.isEmpty()){
            throw exception;
        }
        return true;
    }

    public static boolean validateIngrediente(Ingrediente ingrediente) throws Exception {
        validateNumber(String.valueOf(ingrediente.getId()), new IngredienteException.IngredienteBadRequestException(String.valueOf(ingrediente.getId())), 1);
        validateString(ingrediente.getNombre(), new IngredienteException.IngredienteBadRequestException(ingrediente.getNombre()));
        validateNumber(String.valueOf(ingrediente.getPrecio()), new IngredienteException.IngredienteBadRequestException(String.valueOf(ingrediente.getPrecio())), 0.0);
        return true;
    }

    public static boolean validateHamburgesa(Hamburgesa hamburgesa) throws Exception {
        validateString(hamburgesa.getNombre(), new IngredienteException.IngredienteBadRequestException(hamburgesa.getNombre()));
        List<Ingrediente> ingredientes = hamburgesa.getIngredientes();
        for(int i = 0; i<ingredientes.size(); i++){
            validateIngrediente(ingredientes.get(i));
        }
        return true;
    }

}
