package dto.ingrediente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Root(name = "ingrediente")
public class IngredienteDto {
    @Attribute(name = "id")
    private String id;

    @Attribute(name = "nombre")
    private String nombre;

    @Attribute(name = "precio")
    private String precio;
}
