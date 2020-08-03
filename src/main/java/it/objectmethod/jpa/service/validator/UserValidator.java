package it.objectmethod.jpa.service.validator;

import java.util.ArrayList;
import java.util.List;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.stereotype.Component;

import it.objectmethod.jpa.model.User;
import it.objectmethod.jpa.service.validator.rules.todo.UserRule;

@Component
public class UserValidator implements EntityValidator<User> {

	public List<FieldError> validate(User user) {

		List<FieldError> errors = new ArrayList<FieldError>();

		Rules rules = new Rules();
		Facts facts = new Facts();
		DefaultRulesEngine rulesEngine = new DefaultRulesEngine();

		rules.register(new UserRule());

		facts.put("user", user);
		facts.put("error", errors);

		rulesEngine.fire(rules, facts);

		return errors;
	}

}
