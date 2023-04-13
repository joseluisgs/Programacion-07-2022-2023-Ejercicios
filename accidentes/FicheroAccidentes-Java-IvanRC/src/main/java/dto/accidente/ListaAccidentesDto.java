package dto.accidente;

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
@Root(name = "accidentes")
public class ListaAccidentesDto {
    @Json(name = "accidentes")
    @ElementList(name = "accidentes", inline = true)
    List<AccidenteDto> accidentesDto;
}
