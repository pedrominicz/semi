package io.github.pedrominicz.semi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * Returns all posts in a given category.
     *
     * @param category the category name
     * @return the posts in the category
     */
    public List<Post> findByCategory(final String category) {
        return postRepository.findByCategory(category);
    }

    /**
     * Saves a post. In addition to `users`, the post will also belong to the
     * authenticated user.
     *
     * @param post the post to be saved
     * @return the saved post
     */
    public Post save(final Post post) {
        post.setCategories(categoryService.findByCategoryIn(post.getCategories()));

        return postRepository.save(post);
    }

}