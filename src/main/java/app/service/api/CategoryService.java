package app.service.api;

import java.util.List;

import app.models.Category;

public interface CategoryService extends SeederService {

    List<Category> findAll();

    Category findById(Long id);

    void save(Category category);

    void deleteById(Long id);
}
