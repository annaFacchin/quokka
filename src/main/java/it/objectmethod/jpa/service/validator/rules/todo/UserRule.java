package it.objectmethod.jpa.service.validator.rules.todo;

import java.util.List;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import it.objectmethod.jpa.model.User;
import it.objectmethod.jpa.service.validator.FieldError;

@Rule(name = "userRule", description = "can't be null")
public class UserRule {

	@Condition
	public boolean isTitleNull(@Fact("user") User user) {
		boolean isValid = false;
		if (user.getUsername() != null) {
			isValid = true;
		}
		return !isValid;
	}

	@Action
	public void error(@Fact("error") List<FieldError> errors) {
		FieldError error = new FieldError();
		error.setEntityName("user");
		error.setField("username");
		error.setError("Can't be null");
		errors.add(error);
	}

}
