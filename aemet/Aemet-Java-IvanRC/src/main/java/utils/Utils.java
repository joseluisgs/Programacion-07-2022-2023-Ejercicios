package utils;

import validator.NombreFicheroValidator;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {
    public static LocalTime toLocalTime(String horas) {
        //Asumo que en caso de que falten valores, faltan en el siguiente orden: segundos -> minutos -> horas
        List<Integer> hora = Arrays.stream(horas.split(":")).map(a -> Integer.parseInt(a)).toList();
        LocalTime res;
        if (hora.size() == 3) {
            res = LocalTime.of(hora.get(0), hora.get(1), hora.get(2));
        } else {
            if (hora.size() == 2) {
                res = LocalTime.of(hora.get(0), hora.get(1), 0);
            } else {
                res = LocalTime.of(hora.get(0), 0, 0);
            }
        }
        return res;
    }

    public static LocalDate toLocalDate(String fechas) {
        List<Integer> fecha = Arrays.stream(fechas.split("-")).map(a -> Integer.parseInt(a)).toList();
        return LocalDate.of(
                fecha.get(0), fecha.get(1), fecha.get(2)
        );
    }

    public static ArrayList<Integer> toFormatoFechaValido(String ficha) throws FileNotFoundException {
        NombreFicheroValidator.validateNombreFichero(ficha);
        String fecha = ficha.replace("Aemet", "").replace(".csv", "");
        ArrayList<Integer> fechas = new ArrayList<>();
        fechas.add(Integer.parseInt(fecha.substring(0, 4)));
        fechas.add(Integer.parseInt(fecha.substring(4, 6)));
        fechas.add(Integer.parseInt(fecha.substring(6, 8)));
        return fechas;
    }
}
