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
@Json(name = "medicion")
@Root(name = "medicion")
public class MedicionDto {
    @Element(name = "poblacion", required = false)
    String poblacion = null;

    @Element(name = "provincia", required = false)
    String provincia = null;

    @Element(name = "temperatura_max", required = false)
    String temperaturaMax = null;

    @Element(name = "hora_temperatura_max", required = false)
    String horaTempMax = null;

    @Element(name = "temperatura_min", required = false)
    String temperaturaMin = null;

    @Element(name = "hora_temperatura_min", required = false)
    String horaTempMin = null;

    @Element(name = "precipitacion", required = false)
    String precipitacion = null;

    @Element(name = "dia", required = false)
    String dia = null;
}
