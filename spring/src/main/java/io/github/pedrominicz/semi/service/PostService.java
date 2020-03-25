package io.github.pedrominicz.semi.service;

import java.util.List;
import java.util.Optional;


import io.github.pedrominicz.semi.model.Category;
import io.github.pedrominicz.semi.model.Post;

public interface PostService {

    Iterable<Post> findAll();

    Optional<Post> findById(final Long id);

    List<Post> findByAuthorName(final String name);

    Iterable<Category> findAllCategories();

    List<Post> findByCategoryName(final String name);

    Post save(final Post post);

    Category saveCategory(final Category category);

    void deleteById(final Long id);

    public void deleteCategoryByName(final String name);

}
