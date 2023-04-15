package utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Utils {
    public static LocalTime toLocalTime(String hora) {
        List<Integer> horas = Arrays.stream(hora.split(":")).map(h -> Integer.parseInt(h)).toList();
        // Esto lo pongo debido a que una hora es -> 04:30, en ese caso supuse que se saltaron los segundos ya que eran 0
        if(horas.size() == 2){
            return LocalTime.of(horas.get(0), horas.get(1), 0);
        }
        return LocalTime.of(horas.get(0), horas.get(1), horas.get(2));
    }

    public static LocalDate toLocalDateDDMMYYYY(String fecha) {
        List<String> fechas = new ArrayList<>();
        LocalDate fechaFinal = LocalDate.now();
        if(fecha.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
            fechas = Arrays.stream(fecha.split("/")).toList();
            fechaFinal = LocalDate.of(Integer.parseInt(fechas.get(2)),Integer.parseInt(fechas.get(1)), Integer.parseInt(fechas.get(0)));
        }else{
            if(fecha.matches("[0-9]{2}-[0-9]{2}-[0-9]{4}")){
                fechas = Arrays.stream(fecha.split("-")).toList();
                fechaFinal = LocalDate.of(Integer.parseInt(fechas.get(0)), Integer.parseInt(fechas.get(1)), Integer.parseInt(fechas.get(2)));
            }
        }
        return fechaFinal;
    }

    public static String getValorNumericoToString(Integer numero) {
        String res = "NULL";
        if(numero != null) res = String.valueOf(numero);
        return res;
    }

    public static String getValorNumericoToString(Double numero) {
        String res = "NULL";
        if(numero != null) res = String.valueOf(numero);
        return res;
    }

    public static String getPositivoToString(Boolean esPositiva, String correcto, String incorrecto) {
        String res = correcto;
        if(!esPositiva) res = incorrecto;
        return res;
    }

    public static Integer valorNumericoToInt(String numero) {
        Integer res = null;
        if(numero.matches("[0-9]+")) res = Integer.parseInt(numero);
        return res;
    }

    public static Double valorNumericoToDouble(String numero) {
        Double res = null;
        if(numero.matches("[0-9]+")) res = Double.parseDouble(numero);
        return res;
    }
}
