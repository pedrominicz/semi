package io.github.pedrominicz.semi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.github.pedrominicz.semi.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    List<Category> findByCategoryIn(final List<String> categories);

}