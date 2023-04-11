package dto.distrito;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Root(name = "distrito")
public class DistritoDto {
    @Element(name = "id_distrito")
    String id; // 5
    @Element(name = "nombre")
    String nombre; // 6
}
