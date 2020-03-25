package io.github.pedrominicz.semi.controller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.pedrominicz.semi.model.Category;
import io.github.pedrominicz.semi.model.Post;
import io.github.pedrominicz.semi.service.PostService;

@CrossOrigin
@RequestMapping("api/post")
@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    @JsonView(Post.class)
    public Iterable<Post> findAll() {
        return postService.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Post.class)
    public Post findById(@PathVariable("id") final Long id) {
        return postService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("user/{name}")
    @JsonView(Post.class)
    public List<Post> findByAuthor(@PathVariable("name") final String name) {
        return postService.findByAuthor(name);
    }

    @GetMapping("category")
    @JsonView(Post.class)
    public Iterable<Category> findAllCategories() {
        return postService.findAllCategories();
    }

    @GetMapping("category/{name}")
    @JsonView(Post.class)
    public List<Post> findByCategory(@PathVariable("name") final String name) {
        return postService.findByCategory(name);
    }

    @GetMapping("user/{author}/category/{category}")
    @JsonView(Post.class)
    public List<Post> findByAuthorAndCategory(@PathVariable("author") final String author,
            @PathVariable("category") final String category) {
        return postService.findByAuthorAndCategory(author, category);
    }

    @PostMapping
    public Post save(@RequestBody final Post post) {
        return postService.save(post);
    }

    @PostMapping("category")
    public Category saveCategory(@RequestBody final Category category) {
        return postService.saveCategory(category);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") final Long id) {
        postService.deleteById(id);
    }

    @DeleteMapping("category/{name}")
    public void deleteCategoryByName(@PathVariable("name") final String name) {
        postService.deleteCategoryByName(name);
    }

}