package app.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import app.models.Book;

@Repository
public interface BookRepository extends CrudRepository<Book,Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate date);

    List<Book> getAllByReleaseDateBefore(LocalDate date);

    List<Book> getByAuthorOrderBy();

}
