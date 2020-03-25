package io.github.pedrominicz.semi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.pedrominicz.semi.model.Category;
import io.github.pedrominicz.semi.model.Post;
import io.github.pedrominicz.semi.model.User;
import io.github.pedrominicz.semi.repository.PostRepository;
import io.github.pedrominicz.semi.security.SecurityUtil;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PostRepository postRepository;

    /**
     * Returns all posts.
     *
     * @return the posts
     */
    @PreAuthorize("permitAll()")
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    /**
     * Returns a post and all comments that belong to it.
     *
     * @param id the ID of the post
     * @return the post
     */
    @PreAuthorize("permitAll()")
    public Optional<Post> findById(final Long id) {
        return postRepository.findById(id);
    }

    /**
     * Returns all posts by a given user.
     *
     * @param name the name of the user
     * @return the posts by the user
     */
    @PreAuthorize("permitAll()")
    public List<Post> findByAuthorName(final String name) {
        return postRepository.findByAuthorName(name);
    }

    /**
     * Returns all categories.
     *
     * @return all the categories
     */
    @PreAuthorize("permitAll()")
    public Iterable<Category> findAllCategories() {
        return categoryService.findAll();
    }

    /**
     * Returns all posts in a given category.
     *
     * @param name the category name
     * @return the posts in the category
     */
    @PreAuthorize("permitAll()")
    public List<Post> findByCategoryName(final String name) {
        return postRepository.findByCategoryName(name);
    }

    /**
     * Saves a post. The post will also belong to the authenticated user.
     *
     * @param post the post to be saved
     * @return the saved post
     */
    @PreAuthorize("isAuthenticated()")
    public Post save(final Post post) {
        final User user = SecurityUtil.getAuthenticatedUser();

        post.setAuthor(user);

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
    @PreAuthorize("hasRole('ADMIN')")
    public Category saveCategory(final Category category) {
        return categoryService.save(category);
    }

    /**
     * Deletes a post.
     *
     * @param id the ID of the post
     */
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(final Long id) {
        postRepository.deleteById(id);
    }

}
