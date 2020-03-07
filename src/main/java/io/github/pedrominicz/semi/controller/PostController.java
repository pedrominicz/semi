package io.github.pedrominicz.semi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import io.github.pedrominicz.semi.model.User;
import io.github.pedrominicz.semi.service.CommentService;
import io.github.pedrominicz.semi.service.PostService;
import io.github.pedrominicz.semi.service.UserService;

@CrossOrigin
@RequestMapping("api/post")
@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @GetMapping
    public Iterable<Post> findAll() {
        return postService.findAll();
    }

    @GetMapping(path = "{id}")
    public Post findById(@PathVariable("id") final Long id) {
        final Post post = postService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        post.setComments(commentService.findAllByPostId(id));

        return post;
    }

    @PostMapping
    public Post save(@RequestBody final Post post) {
        return postService.save(post);
    }

    @PostMapping(path = "{id}/comment")
    public Comment saveComment(@PathVariable("id") final Long id, @RequestBody final Comment comment) {
        final String username = SecurityContextHolder.getContext().getAuthentication().getName();

        final User user = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        final Post post = postService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return commentService.save(new Comment(comment.getText(), user, post));
    }

    @DeleteMapping(path = "{id}/comment/{comment_id}")
    public void deleteCommentById(@PathVariable("id") final Long id, @PathVariable("comment_id") final Long comment_id) {
        final Comment comment = commentService.findById(comment_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!comment.getPost().getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        commentService.deleteById(comment_id);
    }
}