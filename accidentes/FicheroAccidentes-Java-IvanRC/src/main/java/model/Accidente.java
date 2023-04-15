package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accidente {
    String numeroExpediente; // 0
    LocalDate fecha; // 1
    LocalTime hora; // 2
    List<String> localizacion; // 3
    Integer numeroCalle; // 4, en caso de que no sea válido pues un nulo
    Distrito distrito;
    String tipoAccidente; // 7
    String estadoMeteorologico; // 8
    String tipoDeVehiculo; // 9
    String tipoDePersona; // 10
    String rangoEdad; // 11
    String sexo; // 12
    Lesividad lesividad;
    Double longitud; // 15
    Double altitud; // 16
    Boolean esPositivaEnAlchol; // 17, N == false y S == true
    Boolean esPositivaEnDrogas; // 18, NULL == false y 1 == true
}
