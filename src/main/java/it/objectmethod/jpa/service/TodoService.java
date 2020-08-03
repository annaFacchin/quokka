package it.objectmethod.jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.objectmethod.jpa.model.Todo;
import it.objectmethod.jpa.repository.TodoRepo;
import it.objectmethod.jpa.service.dto.TodoDTO;
import it.objectmethod.jpa.service.exception.ValidationErrorException;
import it.objectmethod.jpa.service.mapper.TodoMapper;
import it.objectmethod.jpa.service.validator.FieldError;
import it.objectmethod.jpa.service.validator.TodoValidator;

@Service
public class TodoService {

	@Autowired
	private TodoRepo repo;

	@Autowired
	private TodoMapper map;

	@Autowired
	private TodoValidator validator;

	public List<TodoDTO> findAll() {
		List<Todo> todos = repo.findAll();
		return map.toDto(todos);
	}

	public TodoDTO save(TodoDTO todoDto) throws ValidationErrorException {
		Todo newTodo = map.toEntity(todoDto);
		List<FieldError> errors = new ArrayList<>();
		errors.addAll(validator.validate(newTodo));

		if (errors.size() > 0) {
			ValidationErrorException exception = new ValidationErrorException();
			exception.setErrors(errors);
			throw exception;
		} else {
			newTodo = repo.save(newTodo);
			todoDto = map.toDto(newTodo);
		}
		return todoDto;
	}

	public TodoDTO findById(Integer id) {
		Todo opt = repo.findById(id).orElse(null);
		TodoDTO todo = map.toDto(opt);
		return todo;
	}
}
