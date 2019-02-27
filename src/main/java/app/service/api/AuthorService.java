package app.service.api;

import java.util.List;

import app.models.Author;

public interface AuthorService extends SeederService {


    List<Author> findAll();

    Author findById(Long id);

    List<String> getAuthorNamesOrderedByBookCount();

    void save(Author author);

    void deleteById(Long id);
}
