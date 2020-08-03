package it.objectmethod.jpa.service.validator.rules.todo;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import it.objectmethod.jpa.model.Todo;

@Rule(name = "dataRule", description = "if null set new")
public class DateRule {

	@Condition
	public boolean idDateNull(@Fact("date") Todo todo) {

		boolean isValid = false;
		if (todo.getCreationDate() != null) {
			isValid = true;
		}
		return !isValid;
	}

	@Action
	public void setDate(@Fact("todo") Todo todo) {
		LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
		todo.setCreationDate(now);
	}
}
