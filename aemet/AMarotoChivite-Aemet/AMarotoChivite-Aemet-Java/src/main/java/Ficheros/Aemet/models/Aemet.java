package Ficheros.Aemet.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Aemet {
    private final String nombrePoblacion;
    private final String nombreProvincia;
    private final Double temperaturaMaxima;
    private final LocalTime horaTemperaturaMaxima;
    private final Double temperaturaMinima;
    private final LocalTime horaTemperaturaMinima;
    private final Double precipitacion;
    private final LocalDate date;

    public Aemet(String nombrePoblacion, String nombreProvincia, Double temperaturaMaxima, LocalTime horaTemperaturaMaxima, Double temperaturaMinima, LocalTime horaTemperaturaMinima, Double precipitacion, LocalDate date) {
        this.nombrePoblacion = nombrePoblacion;
        this.nombreProvincia = nombreProvincia;
        this.temperaturaMaxima = temperaturaMaxima;
        this.horaTemperaturaMaxima = horaTemperaturaMaxima;
        this.temperaturaMinima = temperaturaMinima;
        this.horaTemperaturaMinima = horaTemperaturaMinima;
        this.precipitacion = precipitacion;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Aemet(nombrePoblacion='" + nombrePoblacion + "', nombreProvincia='" + nombreProvincia + "', temperaturaMaxima=" + temperaturaMaxima + ", horaTemperaturaMaxima=" + horaTemperaturaMaxima + ", temperaturaMinima=" + temperaturaMinima + ", horaTemperaturaMinima=" + horaTemperaturaMinima + ", precipitacion=" + precipitacion + ", date=" + date + ")";
    }

    public String getNombrePoblacion() {
        return nombrePoblacion;
    }

    public String getNombreProvincia() {
        return nombreProvincia;
    }

    public Double getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public LocalTime getHoraTemperaturaMaxima() {
        return horaTemperaturaMaxima;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getPrecipitacion() {
        return precipitacion;
    }

    public LocalTime getHoraTemperaturaMinima() {
        return horaTemperaturaMinima;

    }

    public Double getTemperatureAverage() {
        return (temperaturaMaxima + temperaturaMinima) / 2;
    }

    public Double getTemperaturaMinima() {
        return temperaturaMinima;
    }
}
