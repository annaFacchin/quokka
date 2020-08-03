package it.objectmethod.jpa.service.validator;

import java.util.ArrayList;
import java.util.List;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.stereotype.Component;

import it.objectmethod.jpa.model.Todo;
import it.objectmethod.jpa.service.validator.rules.todo.DateRule;
import it.objectmethod.jpa.service.validator.rules.todo.TitleRule;

@Component
public class TodoValidator implements EntityValidator<Todo> {

	public List<FieldError> validate(Todo todo) {

		List<FieldError> errors = new ArrayList<FieldError>();

		Rules rules = new Rules();
		Facts facts = new Facts();
		DefaultRulesEngine rulesEngine = new DefaultRulesEngine();

		rules.register(new TitleRule());
		rules.register(new DateRule());

		facts.put("todo", todo);
		facts.put("error", errors);
		facts.put("date", todo);
		rulesEngine.fire(rules, facts);

		return errors;
	}

}
