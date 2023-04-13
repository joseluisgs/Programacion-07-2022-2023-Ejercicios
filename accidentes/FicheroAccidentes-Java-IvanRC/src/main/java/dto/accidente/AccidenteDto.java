package dto.accidente;

import dto.distrito.DistritoDto;
import dto.lesividad.LesividadDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Root(name = "accidente")
public class AccidenteDto {
    @Element(name = "numero_de_expediente")
    String numeroExpediente; // 0

    @Element(name = "fecha")
    String fecha; // 1

    @Element(name = "hora")
    String hora; // 2

    @ElementList(name = "localizacion", inline = true)
    List<String> localizacion; // 3

    @Element(name = "numero_calle")
    String numeroCalle; // 4, en caso de que no sea válido pues un nulo

    @Element(name = "distrito")
    DistritoDto distrito;

    @Element(name = "tipo_de_accidente")
    String tipoAccidente; // 7

    @Element(name = "estado_meteorologico")
    String estadoMeteorologico; // 8

    @Element(name = "tipo_de_vehiculo")
    String tipoDeVehiculo; // 9

    @Element(name = "tipo_de_persona")
    String tipoDePersona; // 10

    @Element(name = "rango_de_edad")
    String rangoEdad; // 11

    @Element(name = "sexo")
    String sexo; // 12

    @Element(name = "lesividad")
    LesividadDto lesividad;

    @Element(name = "longitud")
    String longitud; // 15

    @Element(name = "altitud")
    String altitud; // 16

    @Element(name = "es_positiva_en_alchol")
    String esPositivaEnAlchol; // 17, N == false y S == true

    @Element(name = "es_positiva_en_drogas")
    String esPositivaEnDrogas; // 18, NULL == false y 1 == true
}
