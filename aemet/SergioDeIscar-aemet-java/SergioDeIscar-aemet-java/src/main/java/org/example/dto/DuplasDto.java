package org.example.dto;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

@Root(name = "duplas")
public class DuplasDto implements Serializable {
    public DuplasDto(List<DuplaDto> duplas) {
        this.duplas = duplas;
    }
    public DuplasDto(){
        // Constructor vacio para SimpleXML
    }
    @ElementList(name = "dupla", inline = true)
    private List<DuplaDto> duplas;

    //Getters
    public List<DuplaDto> getDuplas() {
        return duplas;
    }

}
