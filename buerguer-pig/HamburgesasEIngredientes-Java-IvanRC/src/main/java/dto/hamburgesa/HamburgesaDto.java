package dto.hamburgesa;


import dto.ingrediente.IngredienteDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Root(name = "hamburgesa")
public class HamburgesaDto {
    @Attribute(name = "id")
    private String id;

    @Attribute(name = "nombre")
    private String nombre;

    @ElementList(name = "ingredientes", inline = true)
    private List<IngredienteDto> ingredientes;
}
