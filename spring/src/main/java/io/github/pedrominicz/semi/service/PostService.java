package io.github.pedrominicz.semi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.pedrominicz.semi.model.Category;
import io.github.pedrominicz.semi.model.Post;
import io.github.pedrominicz.semi.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PostRepository postRepository;

    /**
     * Returns all posts.
     *
     * @return the posts
     */
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    /**
     * Returns a post and all comments that belong to it.
     *
     * @param id the ID of the post
     * @return the post
     */
    public Optional<Post> findById(final Long id) {
        return postRepository.findById(id);
    }

    /**
     * Returns all posts by a given user.
     *
     * @param name the name of the user
     * @return the posts by the user
     */
    public List<Post> findByAuthorName(final String name) {
        return postRepository.findByAuthorName(name);
    }

    /**
     * Returns all categories.
     *
     * @return all the categories
     */
    public Iterable<Category> findAllCategories() {
        return categoryService.findAll();
    }

    /**
     * Returns all posts in a given category.
     *
     * @param name the category name
     * @return the posts in the category
     */
    public List<Post> findByCategoryName(final String name) {
        return postRepository.findByCategoryName(name);
    }

    /**
     * Saves a post. The post will also belong to the authenticated user.
     *
     * @param post the post to be saved
     * @return the saved post
     */
    public Post save(final Post post) {
        final List<Category> categories = post.getCategories();

        post.setCategories(categoryService
                .findByCategoryIn(categories.stream().map(Category::getName).collect(Collectors.toList())));

        return postRepository.save(post);
    }

    /**
     * Saves a category.
     *
     * @param category the category to be saved
     * @return the saved category
     */
    public Category saveCategory(final Category category) {
        return categoryService.save(category);
    }

}