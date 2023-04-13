package org.example.formateadores;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Formateador {
    public static String toFormatString(LocalTime time){
        return time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
    public static LocalTime toLocalTimeFormate(String time){
        String[] split = time.split(":");
        return LocalTime.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]), 0);
    }
}
