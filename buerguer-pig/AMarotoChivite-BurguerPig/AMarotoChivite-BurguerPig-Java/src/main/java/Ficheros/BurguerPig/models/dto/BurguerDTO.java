package Ficheros.BurguerPig.models.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

@Root(name = "hamburguesa") // Para XML
public class BurguerDTO implements Serializable {
    @Attribute(name = "name")
    private String name;

    // El inline es para que no se vea la jerarquía de la lista, y sea más legible
    @ElementList(name = "ingredientes", inline = true)
    private List<IngredienteDTO> ingredients;

    @Attribute(name = "uuid")
    private String uuid;

    @Attribute(name = "precio")
    private String price;

    public BurguerDTO() {
        // Constructor vacío sin argumentos
    }

    public BurguerDTO(
            String name,
            List<IngredienteDTO> ingredients,
            String uuid,
            String price) {
        this.name = name;
        this.ingredients = ingredients;
        this.uuid = uuid;
        this.price = price;
    }

    public String getName() {
        return name;
    }


    public List<IngredienteDTO> getIngredients() {
        return ingredients;
    }


    public String getUuid() {
        return uuid;
    }


    public String getPrice() {
        return price;
    }

}
