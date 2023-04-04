package org.example.dto;

import org.example.models.PrecipitacionInforme;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "precipitacion")
public class PrecipitacionInformeDto {
    @Element(name = "isPrecipitacion")
    private final String isPrecipitacion;
    @Element(name = "value")
    private final String value;

    public PrecipitacionInformeDto(String isPrecipitacion, String value) {
        this.isPrecipitacion = isPrecipitacion;
        this.value = value;
    }

    // Getters
    public String getIsPrecipitacion() {
        return isPrecipitacion;
    }

    public String getValue() {
        return value;
    }

    public PrecipitacionInforme toClass(){
        return new PrecipitacionInforme(Boolean.parseBoolean(isPrecipitacion), Double.parseDouble(value));
    }
}
