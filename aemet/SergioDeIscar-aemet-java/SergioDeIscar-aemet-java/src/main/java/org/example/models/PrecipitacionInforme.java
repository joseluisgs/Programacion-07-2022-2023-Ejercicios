package org.example.models;

import org.example.dto.PrecipitacionInformeDto;

public class PrecipitacionInforme {
    private final Boolean isPrecipitacion;
    private final Double value;

    public PrecipitacionInforme(Boolean isPrecipitacion, Double value) {
        this.isPrecipitacion = isPrecipitacion;
        this.value = value;
    }

    // Getters
    public Boolean getIsPrecipitacion() {
        return isPrecipitacion;
    }

    public Double getValue() {
        return value;
    }

    public PrecipitacionInformeDto toDto(){
        return new PrecipitacionInformeDto(isPrecipitacion.toString(), value.toString());
    }
}
