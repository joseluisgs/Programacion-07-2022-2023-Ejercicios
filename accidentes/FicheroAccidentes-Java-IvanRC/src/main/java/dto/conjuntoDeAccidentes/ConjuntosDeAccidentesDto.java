package dto.conjuntoDeAccidentes;

import com.squareup.moshi.Json;
import dto.accidente.ListaAccidentesDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Root(name = "conjunto_de_accidentes")
public class ConjuntosDeAccidentesDto {
    @Json(name = "conjunto_de_accidentes")
    @ElementList(name = "conjunto_de_accidentes", inline = true)
    ListaAccidentesDto accidentesDto;
}
