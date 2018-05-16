package com.example.demo.Repositories;

import com.example.demo.Class.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Long>{
    Category findByCategoriesIgnoreCase(String category);
}
