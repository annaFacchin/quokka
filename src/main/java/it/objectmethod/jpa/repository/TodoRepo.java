package it.objectmethod.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.objectmethod.jpa.model.Todo;

@Repository
public interface TodoRepo extends JpaRepository<Todo, Integer> {

}
