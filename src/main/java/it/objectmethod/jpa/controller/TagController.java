package it.objectmethod.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.jpa.service.TagService;
import it.objectmethod.jpa.service.dto.TagDTO;

@RestController
@RequestMapping("/api/tag")
public class TagController {

	@Autowired
	private TagService service;

	@GetMapping("/list")
	public List<TagDTO> getTags() {
		List<TagDTO> tags = service.findAll();
		return tags;
	}

}
