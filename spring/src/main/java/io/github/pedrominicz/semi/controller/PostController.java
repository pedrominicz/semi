package io.github.pedrominicz.semi.controller;

import java.util.List;

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
    public Iterable<Post> findAll() {
        return postService.findAll();
    }

    @GetMapping("{id}")
    public Post findById(@PathVariable("id") final Long id) {
        return postService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("user/{name}")
    public List<Post> findByAuthorName(@PathVariable("name") final String name) {
        return postService.findByAuthorName(name);
    }

    @GetMapping("category")
    public Iterable<Category> findAllCategories() {
        return postService.findAllCategories();
    }

    @GetMapping("category/{name}")
    public List<Post> findByCategoryName(@PathVariable("name") final String name) {
        return postService.findByCategoryName(name);
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