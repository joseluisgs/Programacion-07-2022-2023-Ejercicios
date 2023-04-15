package Ficheros.Accidentes.models.dto;

import com.squareup.moshi.Json;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.util.Collection;

public class AccidenteDto {
    @Attribute(name = "num_expediente") // Para XML
    private final String numExpediente;
    @Attribute(name = "fecha")
    private final String fecha;
    @Element(name = "hora")
    private final String hora;
    @Attribute(name = "localizacion")
    private final String localizacion;
    @Attribute(name = "numero")
    private final String numero;
    @Element(name = "cod_distrito")
    private final String codDistrito;
    @Element(name = "distrito")
    private final String distrito;
    @Element(name = "tipo_accidente")
    private final String tipoAccidente;
    @Element(name = "estado_meteorologico")
    private final String estadoMeteorologico;
    @Element(name = "tipo_vehiculo")
    private final String tipoVehiculo;
    @Element(name = "tipo_persona")
    private final String tipoPersona;
    @Element(name = "rango_edad")
    private final String rangoEdad;
    @Element(name = "sexo")
    private final String sexo;
    @Element(name = "cod_lesividad")
    private final String codLesividad;

    @Element(name = "lesividad")
    private final String lesividad;

    @Element(name = "coordenada_x")
    private final String coordenadaX;

    @Element(name = "coordenada_y")
    private final String coordenadaY;

    @Element(name = "positiva_alcohol")
    private final String positivaAlcohol;

    @Element(name = "positiva_droga")
    private final String positivaDroga;

    public AccidenteDto(
            @Attribute(name = "num_expediente") String numExpediente,
            @Attribute(name = "fecha") String fecha,
            @Element(name = "hora") String hora,
            @Attribute(name = "localizacion") String localizacion,
            @Attribute(name = "numero") String numero,
            @Element(name = "cod_distrito") String codDistrito,
            @Element(name = "distrito") String distrito,
            @Element(name = "tipo_accidente") String tipoAccidente,
            @Element(name = "estado_meteorologico") String estadoMeteorologico,
            @Element(name = "tipo_vehiculo") String tipoVehiculo,
            @Element(name = "tipo_persona") String tipoPersona,
            @Element(name = "rango_edad") String rangoEdad,
            @Element(name = "sexo") String sexo,
            @Element(name = "cod_lesividad") String codLesividad,
            @Element(name = "lesividad") String lesividad,
            @Element(name = "coordenada_x") String coordenadaX,
            @Element(name = "coordenada_y") String coordenadaY,
            @Element(name = "positiva_alcohol") String positivaAlcohol,
            @Element(name = "positiva_droga") String positivaDroga
    ) {
        this.numExpediente = numExpediente;
        this.fecha = fecha;
        this.hora = hora;
        this.localizacion = localizacion;
        this.numero = numero;
        this.codDistrito = codDistrito;
        this.distrito = distrito;
        this.tipoAccidente = tipoAccidente;
        this.estadoMeteorologico = estadoMeteorologico;
        this.tipoVehiculo = tipoVehiculo;
        this.tipoPersona = tipoPersona;
        this.rangoEdad = rangoEdad;
        this.sexo = sexo;
        this.codLesividad = codLesividad;
        this.lesividad = lesividad;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.positivaAlcohol = positivaAlcohol;
        this.positivaDroga = positivaDroga;
    }

    public String getPositivaDroga() {
        return positivaDroga;
    }

    public String getPositivaAlcohol() {
        return positivaAlcohol;
    }

    public String getDistrito() {
        return distrito;
    }

    public String getSexo() {
        return sexo;
    }

    public String getFecha() {
        return fecha;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public String getHora() {
        return hora;
    }
    public String getLesividad() {
        return lesividad;
    }

    public String getEstadoMeteorologico() {
        return estadoMeteorologico;
    }

    public String getTipoAccidente() {
        return tipoAccidente;
    }

    @Override
    public String toString() {
        return "AccidenteDto{" +
                "numExpediente='" + numExpediente + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", localizacion='" + localizacion + '\'' +
                ", numero='" + numero + '\'' +
                ", codDistrito='" + codDistrito + '\'' +
                ", distrito='" + distrito + '\'' +
                ", tipoAccidente='" + tipoAccidente + '\'' +
                ", estadoMeteorologico='" + estadoMeteorologico + '\'' +
                ", tipoVehiculo='" + tipoVehiculo + '\'' +
                ", tipoPersona='" + tipoPersona + '\'' +
                ", rangoEdad='" + rangoEdad + '\'' +
                ", sexo='" + sexo + '\'' +
                ", codLesividad='" + codLesividad + '\'' +
                ", lesividad='" + lesividad + '\'' +
                ", coordenadaX='" + coordenadaX + '\'' +
                ", coordenadaY='" + coordenadaY + '\'' +
                ", positivaAlcohol='" + positivaAlcohol + '\'' +
                ", positivaDroga='" + positivaDroga + '\'' +
                '}';
    }

}