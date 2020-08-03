package it.objectmethod.jpa.service.validator;

import java.util.List;

public interface EntityValidator<E> {
	public List<FieldError> validate(E entity);

}
