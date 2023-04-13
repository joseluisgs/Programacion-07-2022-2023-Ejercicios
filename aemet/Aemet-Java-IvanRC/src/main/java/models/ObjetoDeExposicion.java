package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjetoDeExposicion {
    LocalDate dia;
    String lugar;
    Double valor;
    LocalTime momento = null;
}
