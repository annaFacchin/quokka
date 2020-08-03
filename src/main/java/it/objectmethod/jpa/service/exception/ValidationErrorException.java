package it.objectmethod.jpa.service.exception;

import java.util.List;

import it.objectmethod.jpa.service.validator.FieldError;

public class ValidationErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<FieldError> errors;

	public List<FieldError> getErrors() {
		return errors;
	}

	public void setErrors(List<FieldError> errors) {
		this.errors = errors;
	}
}
