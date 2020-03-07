package io.github.pedrominicz.semi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.pedrominicz.semi.model.Comment;
import io.github.pedrominicz.semi.model.Post;
import io.github.pedrominicz.semi.service.CommentService;
import io.github.pedrominicz.semi.service.PostService;

@RequestMapping("api/post")
@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @GetMapping(path = "{id}")
    public Post findById(@PathVariable("id") final Long id) {
        final Post post = postService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        post.setComments(commentService.findAllByPostId(id));

        return post;
    }

    @GetMapping
    public Iterable<Post> findAll() {
        return postService.findAll();
    }

    @PostMapping
    public Post save(@RequestBody final Post post) {
        return postService.save(post);
    }

    @PostMapping(path = "{id}/comment")
    public void saveComment(@PathVariable("id") final Long id, @RequestBody final Comment comment) {
        final Post post = postService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        commentService.save(new Comment(comment.getText(), post));
    }

    @DeleteMapping(path = "{id}/comment/{comment_id}")
    public void deleteCommentById(@PathVariable("comment_id") final Long comment_id) {
        commentService.findById(comment_id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        commentService.deleteById(comment_id);
    }
}