package dto;

import com.squareup.moshi.Json;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Json(name = "registro")
@Root(name = "registro")
public class RegistroDto {

    @Element(name = "dia")
    String dia;

    @Element(name = "temperatura_max", required = false)
    String temperaturaMax = null;

    @Element(name = "temperatura_min", required = false)
    String temperaturaMin = null;

    @Element(name = "temperatura_media", required = false)
    String temperaturaMedia = null;

    @Element(name = "lugar", required = false)
    String lugar = null;

    @Element(name = "momento", required = false)
    String momento = null;

    @Element(name = "hubo_precipitacion", required = false)
    String huboPrecipitacion = null;

    @Element(name = "precipitacion", required = false)
    String precipitacion = null;

    public RegistroDto(String dia, String temperaturaMedia) {
        this.dia = dia;
        this.temperaturaMedia = temperaturaMedia;
    }

    public RegistroDto(String dia, String temperaturaMax, String lugar, String momento) {
        this.dia = dia;
        this.temperaturaMax = temperaturaMax;
        this.lugar = lugar;
        this.momento = momento;
    }

    public RegistroDto(String dia, String temperaturaMin, String lugar, String momento, String campoVacioDiferenciador) {
        this.dia = dia;
        this.temperaturaMin = temperaturaMin;
        this.lugar = lugar;
        this.momento = momento;
    }

    public RegistroDto(String dia, String huboPrecipitacion, String precipitacion) {
        this.dia = dia;
        this.huboPrecipitacion = huboPrecipitacion;
        this.precipitacion = precipitacion;
    }
}
