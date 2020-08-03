package it.objectmethod.jpa.service.mapper;

import org.mapstruct.Mapper;

import it.objectmethod.jpa.model.Todo;
import it.objectmethod.jpa.service.dto.TodoDTO;

@Mapper(componentModel = "spring", uses = { TagMapper.class })
public interface TodoMapper extends EntityMapper<TodoDTO, Todo> {

}
