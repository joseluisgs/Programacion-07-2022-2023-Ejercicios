package Ficheros.BurguerPig.mappers.base;

public interface IDtoMapper<Dto, Model> {
    Dto toDto(Model model);
    Model toModel(Dto dto);
}
