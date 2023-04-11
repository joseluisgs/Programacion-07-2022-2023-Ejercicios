package dto;

import com.squareup.moshi.Json;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Json(name = "informes")
@Root(name = "informes")
public class InformesDto {

    @ElementList(name = "informes", inline = true)
    List<MapaRegistroDto> informes;
}
