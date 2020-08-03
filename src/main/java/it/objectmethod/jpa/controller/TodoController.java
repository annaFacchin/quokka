package it.objectmethod.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.jpa.service.TodoService;
import it.objectmethod.jpa.service.dto.TodoDTO;
import it.objectmethod.jpa.service.exception.ValidationErrorException;
import it.objectmethod.jpa.service.validator.FieldError;

@CrossOrigin
@RestController
@RequestMapping("/api/todo")
public class TodoController {

	@Autowired
	private TodoService service;

	@GetMapping("/list")
	public List<TodoDTO> getTodos() {
		List<TodoDTO> todos = service.findAll();
		return todos;
	}

	@GetMapping("{id}/find")
	public ResponseEntity<TodoDTO> findById(@PathVariable("id") Integer id) {
		ResponseEntity<TodoDTO> response;
		try {
			TodoDTO msDto = service.findById(id);
			response = new ResponseEntity<>(msDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@PostMapping("/save")
	public ResponseEntity<TodoDTO> postOrUpdateTodo(@RequestBody TodoDTO todo) throws ValidationErrorException {
		ResponseEntity<TodoDTO> response = null;

		todo = service.save(todo);
		if (todo != null) {
			response = new ResponseEntity<>(todo, HttpStatus.ACCEPTED);
			System.out.println("new todo created");
		} else {
			response = new ResponseEntity<>(todo, HttpStatus.ACCEPTED);
			System.out.println("todo updated");
		}
		return response;
	}

	@ExceptionHandler({ ValidationErrorException.class })
	public ResponseEntity<List<FieldError>> handlefieldErrors(ValidationErrorException exception) {
		ResponseEntity<List<FieldError>> response = new ResponseEntity<>(exception.getErrors(), HttpStatus.BAD_REQUEST);
		return response;
	}
}
