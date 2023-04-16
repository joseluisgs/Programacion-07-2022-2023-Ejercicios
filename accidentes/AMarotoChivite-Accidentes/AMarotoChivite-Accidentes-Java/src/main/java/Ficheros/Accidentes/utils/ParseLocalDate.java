package Ficheros.Accidentes.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ParseLocalDate {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate parseFecha(String fechaStr) {
        return LocalDate.parse(fechaStr, formatter);
    }
}
