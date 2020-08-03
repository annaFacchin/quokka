package it.objectmethod.jpa.controller.beans;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import it.objectmethod.jpa.model.User;

@Component
public class UserLog {

	private Map<String, User> userLogMap;

	public Map<String, User> getUserMap() {
		if (this.userLogMap == null) {
			this.userLogMap = new HashMap<String, User>();
		}
		return userLogMap;
	}

}
