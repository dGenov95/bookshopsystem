package app.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import app.enums.AgeRestriction;
import app.enums.EditionType;
import app.models.Author;
import app.models.Book;
import app.models.Category;
import app.repository.AuthorRepository;
import app.repository.BookRepository;
import app.repository.CategoryRepository;
import app.service.api.BookService;
import app.utils.FileContentsReader;
import app.utils.IterableToListConverter;

@Service
@Primary
@Transactional
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<Book> findAll() {
        return IterableToListConverter.getList(bookRepository.findAll());
    }

    @Override
    public List<String> getAllTitlesAfter(LocalDate date) {
        return bookRepository.findAllByReleaseDateAfter(date)
            .stream()
            .map(Book::getTitle)
            .collect(Collectors.toList());
    }

    @Override
    public List<String> getAuthorsFirstNameByReleaseDateBefore(LocalDate date) {
        return bookRepository.getAllByReleaseDateBefore(date)
            .stream()
            .map(book -> book.getAuthor().getFirstName())
            .collect(Collectors.toList());
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id)
            .orElse(null);
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void seedData(String seedFilePath) throws IOException {

        if (bookRepository.count() != 0) {
            return;
        }
        Arrays.stream(FileContentsReader.getFileLines(seedFilePath))
            .forEach(bookLine -> {
                String[] lineArgs = bookLine.split("\\s+");

                Book book = new Book();

                book.setAuthor(getRandomAuthor());

                EditionType editionType = EditionType.values()[Integer.parseInt(lineArgs[0])];
                book.setEditionType(editionType);

                LocalDate releaseDate = LocalDate.parse(lineArgs[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
                book.setReleaseDate(releaseDate);

                book.setCopies(Integer.parseInt(lineArgs[2]));
                book.setPrice(new BigDecimal(lineArgs[3]));

                AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(lineArgs[4])];
                book.setAgeRestriction(ageRestriction);

                StringBuilder title = new StringBuilder();
                for (int i = 5; i < lineArgs.length; i++) {
                    title.append(lineArgs[i])
                        .append(" ");
                }
                book.setTitle(title.toString()
                    .trim());
                book.setCategories(getRandomCategories());

                bookRepository.save(book);
            });
    }

    private Author getRandomAuthor() {
        Random random = new Random();
        int randomId = random.nextInt((int) (authorRepository.count() - 1)) + 1;
        return authorRepository.findById((long) randomId)
            .orElse(null);
    }

    private Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();
        Random random = new Random();
        int categoriesLength = random.nextInt(5);
        for (int i = 0; i < categoriesLength; i++) {
            Category category = getRandomCategory();
            categories.add(category);
        }
        return categories;
    }

    private Category getRandomCategory() {
        Random random = new Random();
        int randomId = random.nextInt((int) categoryRepository.count() - 1) + 1;
        return categoryRepository.findById((long) randomId)
            .orElse(null);
    }
}
