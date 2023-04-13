package dto.lesividad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Root(name = "lesividad")
public class LesividadDto {
    @Element(name = "id_lesividad")
    String id; // 13
    @Element(name = "lesividad")
    String lesividad; // 14
}
