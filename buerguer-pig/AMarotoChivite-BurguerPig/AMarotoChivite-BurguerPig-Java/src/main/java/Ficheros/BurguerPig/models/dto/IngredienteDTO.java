package Ficheros.BurguerPig.models.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

@Root(name = "ingrediente")
public class IngredienteDTO implements Serializable {

    @Attribute(name = "name")
    private String name;

    @Attribute(name = "precio")
    private String price;

    @Attribute(name = "id")
    private String id;

    public IngredienteDTO() {
        // Constructor vacío sin argumentos
    }

    public IngredienteDTO(String name, String price, String id) {
        this.name = name;
        this.price = price;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }
}

