package app.service.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import app.models.Category;
import app.repository.CategoryRepository;
import app.service.api.CategoryService;
import app.utils.FileContentsReader;
import app.utils.IterableToListConverter;

@Service
@Primary
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return IterableToListConverter.getList(categoryRepository.findAll());
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
            .orElse(null);
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void seedData(String seedFilePath) throws IOException {
        if (categoryRepository.count() != 0) {
            return;
        }

        Arrays.stream(FileContentsReader.getFileLines(seedFilePath))
            .forEach(categoryLine -> {
                Category category = new Category();
                category.setName(categoryLine);
                categoryRepository.save(category);

            });
    }
}
