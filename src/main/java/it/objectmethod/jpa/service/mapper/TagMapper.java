package it.objectmethod.jpa.service.mapper;

import org.mapstruct.Mapper;

import it.objectmethod.jpa.model.Tag;
import it.objectmethod.jpa.service.dto.TagDTO;

@Mapper(componentModel = "spring", uses = { TodoMapper.class })
public interface TagMapper extends EntityMapper<TagDTO, Tag> {

}
