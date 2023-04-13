package org.example.dto;

import org.example.models.Dupla;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.time.LocalDate;

import static org.example.formateadores.Formateador.toLocalTimeFormate;

@Root(name = "dupla")
public class DuplaDto  implements Serializable {
    @Attribute(name = "poblacion")
    private String poblacion;
    @Attribute(name = "provincia")
    private String provincia;
    @Element(name = "temMax")
    private String temMax;
    @Element(name = "timeMax")
    private String timeMax;
    @Element(name = "temMin")
    private String temMin;
    @Element(name = "timeMin")
    private String timeMin;
    @Element(name = "precipitacion")
    private String precipitacion;
    @Attribute(name = "day")
    private String day;
    public DuplaDto(String poblacion, String provincia, String temMax, String timeMax, String temMin, String timeMin, String precipitacion, String day)  {
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.temMax = temMax;
        this.timeMax = timeMax;
        this.temMin = temMin;
        this.timeMin = timeMin;
        this.precipitacion = precipitacion;
        this.day = day;
    }
    public DuplaDto(){
        // Constructor vacio para SimpleXML
    }

    public Dupla toClass(){
        return new Dupla(
                poblacion,
                provincia,
                Double.parseDouble(temMax),
                toLocalTimeFormate(timeMax),
                Double.parseDouble(temMin),
                toLocalTimeFormate(timeMin),
                Double.parseDouble(precipitacion),
                LocalDate.parse(day)
        );
    }

    // Getters
    public String getPoblacion() {
        return poblacion;
    }
    public String getProvincia() {
        return provincia;
    }
    public String getTemMax() {
        return temMax;
    }
    public String getTimeMax() {
        return timeMax;
    }
    public String getTemMin() {
        return temMin;
    }
    public String getTimeMin() {
        return timeMin;
    }
    public String getPrecipitacion() {
        return precipitacion;
    }
    public String getDay() {
        return day;
    }
}
