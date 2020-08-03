package it.objectmethod.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.objectmethod.jpa.model.Tag;

@Repository
public interface TagRepo extends JpaRepository<Tag, Integer> {

}
