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
@Json(name = "persona")
@Root(name = "persona")
public class PersonaDto {
    @Json(name = "nombre")
    @Element(name = "nombre")
    String nombre;

    @Json(name = "edad")
    @Element(name = "edad", required = false)
    String edad;

    @Json(name = "modulo")
    @Element(name = "modulo", required = false)
    String modulo;

    @Json(name = "tipo")
    @Element(name = "tipo")
    String tipo;
}
