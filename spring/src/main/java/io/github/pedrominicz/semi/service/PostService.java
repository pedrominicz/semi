package io.github.pedrominicz.semi.service;

import java.util.List;
import java.util.Optional;


import io.github.pedrominicz.semi.model.Category;
import io.github.pedrominicz.semi.model.Post;

public interface PostService {

    Iterable<Post> findAll();

    Optional<Post> findById(final Long id);

    List<Post> findByAuthor(final String name);

    Iterable<Category> findAllCategories();

    List<Post> findByCategory(final String name);

    List<Post> findByAuthorAndCategory(final String author, final String category);

    Post save(final Post post);

    Category saveCategory(final Category category);

    void deleteById(final Long id);

    public void deleteCategoryByName(final String name);

}
