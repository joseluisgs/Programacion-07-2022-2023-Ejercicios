package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medicion {
    String poblacion;
    String provincia;
    Double temperaturaMax;
    LocalTime horaTempMax;
    Double temperaturaMin;
    LocalTime horaTempMin;
    Double precipitacion;
    LocalDate dia;

    public LocalTime getTempMax(){
        return horaTempMax;
    }

    public Double getTemperaturaMedia(){
        return (temperaturaMax+temperaturaMin)/2;
    }
}
