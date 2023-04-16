package Ficheros.BurguerPig.mappers.base;

import java.util.List;
import java.util.stream.Collectors;

public class ListMapper<Dto, Model> implements IDtoMapper<ListDto<Dto>, List<Model>> {

    private IDtoMapper<Dto, Model> objectMapper;

    public ListMapper(IDtoMapper<Dto, Model> objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public ListDto<Dto> toDto(List<Model> model) {
        List<Dto> dtoList = model.stream().map(objectMapper::toDto).collect(Collectors.toList());
        return new ListDto<>(dtoList);
    }

    @Override
    public List<Model> toModel(ListDto<Dto> dto) {
        return dto.list.stream().map(objectMapper::toModel).collect(Collectors.toList());
    }
}
