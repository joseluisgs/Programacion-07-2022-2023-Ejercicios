package org.example.dto;

import org.example.models.Informe;
import org.example.models.PrecipitacionInforme;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.time.LocalDate;

@Root(name = "informe")
public class InformeDto {
    @Attribute(name = "day")
    private final String day;
    @Element(name = "tem_media")
    private final String temMedia;
    @Element(name = "tem_max")
    private final TemperaturaInformeDto temMax;
    @Element(name = "tem_min")
    private final TemperaturaInformeDto temMin;
    @Element(name = "precipitacion")
    private final PrecipitacionInformeDto precipitacion;

    public InformeDto(String day, String temMedia, TemperaturaInformeDto temMax, TemperaturaInformeDto temMin, PrecipitacionInformeDto precipitacion) {
        this.day = day;
        this.temMedia = temMedia;
        this.temMax = temMax;
        this.temMin = temMin;
        this.precipitacion = precipitacion;
    }

    // Getters
    public String getDay() {
        return day;
    }
    public String getTemMedia() {
        return temMedia;
    }
    public TemperaturaInformeDto getTemMax() {
        return temMax;
    }
    public TemperaturaInformeDto getTemMin() {
        return temMin;
    }
    public PrecipitacionInformeDto getPrecipitacion() {
        return precipitacion;
    }

    public Informe toClass(){
        return new Informe(LocalDate.parse(day), Double.parseDouble(temMedia), temMax.toClass(), temMin.toClass(), precipitacion.toClass());
    }
}
