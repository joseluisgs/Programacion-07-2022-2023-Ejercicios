package Ficheros.BurguerPig.mappers.base;

import java.util.List;

public class ListDto<Dto> {
    public List<Dto> list;

    public ListDto(List<Dto> list) {
        this.list = list;
    }
}

