package app.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import app.models.Author;
import app.repository.AuthorRepository;
import app.service.api.AuthorService;
import app.utils.FileContentsReader;
import app.utils.IterableToListConverter;

@Service
@Primary
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return IterableToListConverter.getList(authorRepository.findAll());
    }



    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id)
            .orElse(null);
    }

    @Override
    public List<String> getAuthorNamesOrderedByBookCount() {
        return null; //TODO
    }

    @Override
    public void save(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public void seedData(String seedFilePath) throws IOException {
        if (authorRepository.count() != 0) {
            return;
        }

        Arrays.stream(FileContentsReader.getFileLines(seedFilePath))
            .forEach(authorLine -> {
                String[] lineArgs = authorLine.split("\\s+");
                Author author = new Author();
                author.setFirstName(lineArgs[0]);
                author.setLastName(lineArgs[1]);
                authorRepository.save(author);
            });
    }
}
