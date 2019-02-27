package app.service.api;

import java.time.LocalDate;
import java.util.List;

import app.models.Book;

public interface BookService extends SeederService {

    List<Book> findAll();

    List<String> getAllTitlesAfter(LocalDate date);

    List<String> getAuthorsFirstNameByReleaseDateBefore(LocalDate date);

    Book findById(Long id);

    void save(Book book);

    void deleteById(Long id);
}
