package dto;

import com.squareup.moshi.Json;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Root;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Json(name = "mapa_registro")
@Root(name = "mapa_registro")
public class MapaRegistroDto {

    @ElementMap(name ="mapa_registros", inline = true)
    Map<String, RegistroDto> mapaRegistros;
}
