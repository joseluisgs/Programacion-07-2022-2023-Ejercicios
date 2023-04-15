package org.example.models;

import org.example.dto.InformeDto;

import java.time.LocalDate;

public class Informe {
    private final LocalDate day;
    private final Double temMedia;
    private final TemperaturaInforme temMax;
    private final TemperaturaInforme temMin;
    private final PrecipitacionInforme precipitacion;

    public Informe(LocalDate day, Double temMedia, TemperaturaInforme temMax, TemperaturaInforme temMin, PrecipitacionInforme precipitacion) {
        this.day = day;
        this.temMedia = temMedia;
        this.temMax = temMax;
        this.temMin = temMin;
        this.precipitacion = precipitacion;
    }

    // Getters
    public LocalDate getDay() {
        return day;
    }
    public Double getTemMedia() {
        return temMedia;
    }
    public TemperaturaInforme getTemMax() {
        return temMax;
    }
    public TemperaturaInforme getTemMin() {
        return temMin;
    }
    public PrecipitacionInforme getPrecipitacion() {
        return precipitacion;
    }

    public InformeDto toDto()
    {
        return new InformeDto(day.toString(), temMedia.toString(), temMax.toDto(), temMin.toDto(), precipitacion.toDto());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Informe informe = (Informe) o;
        return day.equals(informe.day) && temMedia.equals(informe.temMedia) && temMax.equals(informe.temMax) && temMin.equals(informe.temMin) && precipitacion.equals(informe.precipitacion);
    }
}

