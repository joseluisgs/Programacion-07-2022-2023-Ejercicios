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
    private final String poblacion;
    @Attribute(name = "provincia")
    private final String provincia;
    @Element(name = "temMax")
    private final String temMax;
    @Element(name = "timeMax")
    private final String timeMax;
    @Element(name = "temMin")
    private final String temMin;
    @Element(name = "timeMin")
    private final String timeMin;
    @Element(name = "precipitacion")
    private final String precipitacion;
    @Attribute(name = "day")
    private final String day;
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
}
