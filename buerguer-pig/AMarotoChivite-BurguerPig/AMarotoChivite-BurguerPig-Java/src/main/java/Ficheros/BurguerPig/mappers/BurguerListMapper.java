package Ficheros.BurguerPig.mappers;

import Ficheros.BurguerPig.mappers.base.IDtoMapper;
import Ficheros.BurguerPig.mappers.base.ListMapper;
import Ficheros.BurguerPig.models.Burguer;
import Ficheros.BurguerPig.models.dto.BurguerDTO;
import Ficheros.BurguerPig.models.dto.BurguerListDto;

import java.util.List;
import java.util.stream.Collectors;

public class BurguerListMapper extends ListMapper<BurguerDTO, Burguer> {

    public BurguerListMapper(IDtoMapper<BurguerDTO, Burguer> objectMapper) {
        super(objectMapper);
    }

    public BurguerListDto toDtoList(List<Burguer> modelList) {
        List<BurguerDTO> dtoList = modelList.stream().map(burguer -> new BurguerMapper().toDto(burguer)).collect(Collectors.toList());
        return new BurguerListDto(dtoList);
    }

    public List<Burguer> toModelList(BurguerListDto dtoList) {
        return dtoList.getBurguers().stream().map(burguerDto -> new BurguerMapper().toModel(burguerDto)).collect(Collectors.toList());
    }
}
