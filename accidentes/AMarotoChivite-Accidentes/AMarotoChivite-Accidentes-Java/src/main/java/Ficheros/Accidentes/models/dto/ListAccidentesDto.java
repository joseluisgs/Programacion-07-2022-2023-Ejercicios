package Ficheros.Accidentes.models.dto;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "listAccidentes")
public class ListAccidentesDto {
    @ElementList(name = "lista_accidente", entry = "accidente", inline = true)
    private List<AccidenteDto> myList;

    public List<AccidenteDto> getMyList() {
        return myList;
    }

    public void setMyList(List<AccidenteDto> myList) {
        this.myList = myList;
    }
}
