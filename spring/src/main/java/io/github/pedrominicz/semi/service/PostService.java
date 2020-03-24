package io.github.pedrominicz.semi.service;

import java.util.List;
import java.util.Optional;


import io.github.pedrominicz.semi.model.Category;
import io.github.pedrominicz.semi.model.Post;

public interface PostService {

    /**
     * Returns all posts.
     *
     * @return the posts
     */
    Iterable<Post> findAll();

    /**
     * Returns a post and all comments that belong to it.
     *
     * @param id the ID of the post
     * @return the post
     */
    Optional<Post> findById(final Long id);

    /**
     * Returns all posts by a given user.
     *
     * @param name the name of the user
     * @return the posts by the user
     */
    List<Post> findByAuthorName(final String name);

    /**
     * Returns all categories.
     *
     * @return all the categories
     */
    Iterable<Category> findAllCategories();

    /**
     * Returns all posts in a given category.
     *
     * @param name the category name
     * @return the posts in the category
     */
    List<Post> findByCategoryName(final String name);

    /**
     * Saves a post. The post will also belong to the authenticated user.
     *
     * @param post the post to be saved
     * @return the saved post
     */
    Post save(final Post post);

    /**
     * Saves a category.
     *
     * @param category the category to be saved
     * @return the saved category
     */
    Category saveCategory(final Category category);

    /**
     * Deletes a post.
     *
     * @param id the ID of the post
     */
    void deleteById(final Long id);

}
