package validator;

import java.io.FileNotFoundException;

public class NombreFicheroValidator {
    public static boolean validateNombreFichero(String nombre) throws FileNotFoundException {
        var regexNombreFicheroCsv = "Aemet[0-9]{8}.csv";
        if(!nombre.matches(regexNombreFicheroCsv)){
            throw new FileNotFoundException("Ese nombre de fichero no es valido, debe ser, como: Aemet20170101.csv");
        }
        return true;
    }
}
