package app.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import app.models.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author,Long> {
}
