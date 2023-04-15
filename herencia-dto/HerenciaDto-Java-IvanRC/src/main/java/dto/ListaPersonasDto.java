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
@Json(name = "personas")
@Root(name = "personas")
public class ListaPersonasDto {
    @Json(name = "personas")
    @ElementList(name = "personas", inline = true)
    List<PersonaDto> personaDtos;
}
