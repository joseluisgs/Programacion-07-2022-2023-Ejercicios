package dto.ingrediente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Root(name = "ingredientes")
public class ListaIngredienteDto {
    @ElementList(name = "ingredientes", inline = true)
    public List<IngredienteDto> ingredientesDto;
}
