package io.github.pedrominicz.semi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.pedrominicz.semi.model.Category;
import io.github.pedrominicz.semi.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findByCategoryIn(final List<Category> categories) {
        return categoryRepository
                .findByCategoryIn(categories.stream().map(Category::getCategory).collect(Collectors.toList()));
    }

}