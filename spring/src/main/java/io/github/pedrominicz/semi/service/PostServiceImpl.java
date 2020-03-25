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

    @PreAuthorize("permitAll()")
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    @PreAuthorize("permitAll()")
    public Optional<Post> findById(final Long id) {
        return postRepository.findById(id);
    }

    @PreAuthorize("permitAll()")
    public List<Post> findByAuthorName(final String name) {
        return postRepository.findByAuthorName(name);
    }

    @PreAuthorize("permitAll()")
    public Iterable<Category> findAllCategories() {
        return categoryService.findAll();
    }

    @PreAuthorize("permitAll()")
    public List<Post> findByCategoryName(final String name) {
        return postRepository.findByCategoryName(name);
    }

    @PreAuthorize("isAuthenticated()")
    public Post save(final Post post) {
        final User user = SecurityUtil.getAuthenticatedUser();

        post.setAuthor(user);

        final List<Category> categories = post.getCategories();

        post.setCategories(categoryService
                .findByCategoryIn(categories.stream().map(Category::getName).collect(Collectors.toList())));

        return postRepository.save(post);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Category saveCategory(final Category category) {
        return categoryService.save(category);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(final Long id) {
        postRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCategoryByName(final String name) {
        categoryService.deleteByName(name);
    }

}
