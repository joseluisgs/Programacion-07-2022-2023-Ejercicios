package Ficheros.BurguerPig.models.dto;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

@Root(name = "ingredientes")
public class IngredienteListDto implements Serializable {

    @ElementList(name = "ingrediente", inline = true)
    private List<IngredienteDTO> ingredients;

    public IngredienteListDto(List<IngredienteDTO> ingredients) {
        this.ingredients = ingredients;
    }

}
