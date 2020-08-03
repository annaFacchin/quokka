package it.objectmethod.jpa.service.validator.rules.todo;

import java.util.List;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import it.objectmethod.jpa.model.Todo;
import it.objectmethod.jpa.service.validator.FieldError;

@Rule(name = "titoloRule", description = "not null")
public class TitleRule {

	@Condition
	public boolean isTitleNull(@Fact("todo") Todo todo) {
		boolean isValid = false;
		if (todo.getTitle() != null) {
			isValid = true;
		}
		return !isValid;
	}

	@Action
	public void error(@Fact("error") List<FieldError> errors) {
		FieldError error = new FieldError();
		error.setEntityName("todo");
		error.setField("title");
		error.setError("Inserisci un titolo");
		errors.add(error);
	}

}
