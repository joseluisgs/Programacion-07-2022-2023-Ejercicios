package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.DoubleStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Registro {
    LocalDate dia;
    Double temperaturaMax = null;
    Double temperaturaMin = null;
    Double temperaturaMedia = null;
    String lugar = null;
    LocalTime momento = null;
    String huboPrecipitacion = null;
    Double precipitacion = null;

    public Registro(LocalDate dia, Double temperaturaMedia) {
        this.dia = dia;
        this.temperaturaMedia = temperaturaMedia;
    }

    public Registro(LocalDate dia, Double temperaturaMax, String lugar, LocalTime momento) {
        this.dia = dia;
        this.temperaturaMax = temperaturaMax;
        this.lugar = lugar;
        this.momento = momento;
    }

    public Registro(LocalDate dia, Double temperaturaMin, String lugar, LocalTime momento, String campoVacioDiferenciador) {
        this.dia = dia;
        this.temperaturaMin = temperaturaMin;
        this.lugar = lugar;
        this.momento = momento;
    }

    public Registro(LocalDate dia, String huboPrecipitacion, Double precipitacion) {
        this.dia = dia;
        this.huboPrecipitacion = huboPrecipitacion;
        this.precipitacion = precipitacion;
    }
}
