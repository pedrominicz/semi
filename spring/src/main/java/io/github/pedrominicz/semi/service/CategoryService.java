package io.github.pedrominicz.semi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.pedrominicz.semi.model.Category;
import io.github.pedrominicz.semi.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    public List<Category> findByCategoryIn(final List<String> names) {
        return categoryRepository.findByNameIn(names);
    }

    public Category save(final Category category) {
        return categoryRepository.save(category);
    }

}