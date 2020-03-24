package io.github.pedrominicz.semi.service;

import java.util.List;

import io.github.pedrominicz.semi.model.Category;

public interface CategoryService {

    Iterable<Category> findAll();

    List<Category> findByCategoryIn(final List<String> names);

    Category save(final Category category);

}