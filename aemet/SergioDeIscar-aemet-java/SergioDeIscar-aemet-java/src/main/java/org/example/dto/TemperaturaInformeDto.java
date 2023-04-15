package org.example.dto;

import org.example.models.TemperaturaInforme;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.time.LocalTime;

@Root(name = "temperatura")
public class TemperaturaInformeDto {
    @Attribute(name = "poblacion")
    private final String poblacion;
    @Element(name = "value")
    private final String tem;
    @Element(name = "time")
    private final String time;

    public TemperaturaInformeDto(String poblacion, String tem, String time) {
        this.poblacion = poblacion;
        this.tem = tem;
        this.time = time;
    }

    // Getters
    public String getPoblacion() {
        return poblacion;
    }
    public String getTem() {
        return tem;
    }
    public String getTime() {
        return time;
    }

    public TemperaturaInforme toClass(){
        return new TemperaturaInforme(poblacion, Double.parseDouble(tem), LocalTime.parse(time));
    }
}
