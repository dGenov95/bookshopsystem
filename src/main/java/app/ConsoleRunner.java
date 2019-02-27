package app;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import app.service.api.AuthorService;
import app.service.api.BookService;
import app.service.api.CategoryService;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner {
    private static final String RESOURCES_BASE_PATH = "D:\\Workspace\\springdataintro\\src\\main\\resources\\db";

    private AuthorService authorService;
    private CategoryService categoryService;
    private BookService bookService;

    @Autowired
    public ConsoleRunner(AuthorService authorService, CategoryService categoryService, BookService bookService){
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedDatabase();
//        bookService.getAllTitlesAfter(LocalDate.parse("2000-12-31")).forEach(System.out::println);
        bookService.getAuthorsFirstNameByReleaseDateBefore(LocalDate.parse("1990-01-01")).forEach(System.out::println);
    }

    private void seedDatabase() throws IOException{
        authorService.seedData(RESOURCES_BASE_PATH + "\\authors.txt");
        categoryService.seedData(RESOURCES_BASE_PATH + "\\categories.txt");
        bookService.seedData(RESOURCES_BASE_PATH + "\\books.txt");
    }
}
