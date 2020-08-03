package it.objectmethod.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.objectmethod.jpa.model.Tag;
import it.objectmethod.jpa.repository.TagRepo;
import it.objectmethod.jpa.service.dto.TagDTO;
import it.objectmethod.jpa.service.mapper.TagMapper;

@Service
public class TagService {

	@Autowired
	private TagRepo repo;

	@Autowired
	private TagMapper map;

	public List<TagDTO> findAll() {
		List<Tag> tags = repo.findAll();
		return map.toDto(tags);
	}

}
