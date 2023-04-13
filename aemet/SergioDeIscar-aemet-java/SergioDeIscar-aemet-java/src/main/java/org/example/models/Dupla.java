package org.example.models;

import org.example.dto.DuplaDto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.example.formateadores.Formateador.toFormatString;

public class Dupla implements Serializable {
    private final String poblacion;
    private final String provincia;
    private final Double temMax;
    private final LocalTime timeMax;
    private final Double temMin;
    private final LocalTime timeMin;
    private final Double precipitacion;
    private final LocalDate day;
    public Dupla(String poblacion, String provincia, Double temMax, LocalTime timeMax, Double temMin, LocalTime timeMin, Double precipitacion, LocalDate day) {
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.temMax = temMax;
        this.timeMax = timeMax;
        this.temMin = temMin;
        this.timeMin = timeMin;
        this.precipitacion = precipitacion;
        this.day = day;
    }
    public DuplaDto toDto(){
        return new DuplaDto(
                poblacion,
                provincia,
                Double.toString(temMax),
                toFormatString(timeMax),
                Double.toString(temMin),
                toFormatString(timeMin),
                Double.toString(precipitacion),
                day.toString()
        );
    }

    // Getters
    public String getPoblacion() {
        return poblacion;
    }
    public String getProvincia() {
        return provincia;
    }
    public Double getTemMax() {
        return temMax;
    }
    public LocalTime getTimeMax() {
        return timeMax;
    }
    public Double getTemMin() {
        return temMin;
    }
    public LocalTime getTimeMin() {
        return timeMin;
    }
    public Double getPrecipitacion() {
        return precipitacion;
    }
    public LocalDate getDay() {
        return day;
    }

    @Override
    public String toString() {
        return "Dupla{" +
                "poblacion='" + poblacion + '\'' +
                ", provincia='" + provincia + '\'' +
                ", temMax=" + temMax +
                ", timeMax=" + timeMax +
                ", temMin=" + temMin +
                ", timeMin=" + timeMin +
                ", precipitacion=" + precipitacion +
                ", day=" + day +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dupla dupla = (Dupla) o;
        return poblacion.equals(dupla.poblacion) && provincia.equals(dupla.provincia) && temMax.equals(dupla.temMax) && timeMax.equals(dupla.timeMax) && temMin.equals(dupla.temMin) && timeMin.equals(dupla.timeMin) && precipitacion.equals(dupla.precipitacion) && day.equals(dupla.day);
    }
}
