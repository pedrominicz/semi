package io.github.pedrominicz.semi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.pedrominicz.semi.model.Post;
import io.github.pedrominicz.semi.service.PostService;

@RequestMapping("api/post")
@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping(path = "{id}")
    public Post findById(@PathVariable("id") final Long id) {
        return postService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public Iterable<Post> findAll() {
        return postService.findAll();
    }

    @PostMapping
    public Post save(@RequestBody final Post post) {
        return postService.save(post);
    }
}