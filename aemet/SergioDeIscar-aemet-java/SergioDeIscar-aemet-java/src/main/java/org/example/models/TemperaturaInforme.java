package org.example.models;

import org.example.dto.TemperaturaInformeDto;

import java.time.LocalTime;

public class TemperaturaInforme {
    private final String poblacion;
    private final Double tem;
    private final LocalTime time;

    public TemperaturaInforme(String poblacion, Double tem, LocalTime time) {
        this.poblacion = poblacion;
        this.tem = tem;
        this.time = time;
    }

    // Getters
    public String getPoblacion() {
        return poblacion;
    }

    public Double getTem() {
        return tem;
    }

    public LocalTime getTime() {
        return time;
    }

    public TemperaturaInformeDto toDto(){
        return new TemperaturaInformeDto(poblacion, tem.toString(), time.toString());
    }
}
