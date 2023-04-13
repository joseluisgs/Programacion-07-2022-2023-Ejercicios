package org.example.dto;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "informes")
public class InformesDto {
    @ElementList(name = "informe", inline = true)
    private final List<InformeDto> informes;

    public InformesDto(List<InformeDto> informesDto){
        this.informes = informesDto;
    }

    //Getters
    public List<InformeDto> getInformes(){
        return informes;
    }
}
