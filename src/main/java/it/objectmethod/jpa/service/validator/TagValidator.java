package it.objectmethod.jpa.service.validator;

import java.util.ArrayList;
import java.util.List;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.core.DefaultRulesEngine;

import it.objectmethod.jpa.model.Tag;

public class TagValidator implements EntityValidator<Tag> {

	public List<FieldError> validate(Tag tag) {
		List<FieldError> errors = new ArrayList<FieldError>();

		Rules rules = new Rules();
		Facts facts = new Facts();
		DefaultRulesEngine rulesEngine = new DefaultRulesEngine();

		facts.put("tag", tag);
		facts.put("errors", errors);
		rulesEngine.fire(rules, facts);

		return errors;

	}

}
