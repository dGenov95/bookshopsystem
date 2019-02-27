package app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import app.models.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Long> {
}
