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
@Json(name = "mapa_mediciones")
@Root(name = "mapa_mediciones")
public class MapaMedicionesDto {

    @ElementMap(name ="mapa_mediciones", inline = true)
    Map<String, ListaMedicionesDto> mapaMediciones;
}
