package Ficheros.BurguerPig.models.dto;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

@Root(name = "hamburguesas")
public class BurguerListDto implements Serializable {
    @ElementList(name = "hamburguesa", inline = true)
    private List<BurguerDTO> burguers;

    public BurguerListDto() {
        // Constructor vacío sin argumentos, para poder escribir
    }
    public BurguerListDto(List<BurguerDTO> burguers) {
        this.burguers = burguers;
    }

    public List<BurguerDTO> getBurguers() {
        return burguers;
    }

}
