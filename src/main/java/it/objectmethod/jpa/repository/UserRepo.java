package it.objectmethod.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.objectmethod.jpa.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	User findByUsernameAndPassword(String username, String password);

}
