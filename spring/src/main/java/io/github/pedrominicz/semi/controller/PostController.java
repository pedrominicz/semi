package io.github.pedrominicz.semi.controller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import io.github.pedrominicz.semi.security.SecurityUtil;
import io.github.pedrominicz.semi.service.PostService;

@CrossOrigin
@RequestMapping("api/post")
@RestController
public class PostController {

    @Autowired
    private PostService postService;

    /**
     * Returns all posts.
     *
     * @return the posts
     */
    @GetMapping
    @JsonView(Post.Summary.class)
    @PreAuthorize("permitAll()")
    public Iterable<Post> findAll() {
        return postService.findAll();
    }

    /**
     * Returns a post and all comments that belong to it.
     *
     * @param id the ID of the post
     * @return the post
     */
    @GetMapping(path = "{id}")
    @PreAuthorize("permitAll()")
    public Post findById(@PathVariable("id") final Long id) {
        return postService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Returns all posts by a given user.
     *
     * @param username the name of the user
     * @return the posts by the user
     */
    @GetMapping("user/{username}")
    @JsonView(Post.Summary.class)
    @PreAuthorize("permitAll()")
    public List<Post> findByAuthorId(@PathVariable("username") final String username) {
        return postService.findByAuthorUsername(username);
    }

    /**
     * Returns all posts in a given category.
     *
     * @param category the category name
     * @return the posts in the category
     */
    @GetMapping("category/{category}")
    @JsonView(Post.Summary.class)
    @PreAuthorize("permitAll()")
    public List<Post> findByCategory(@PathVariable("category") final String category) {
        return postService.findByCategory(category);
    }

    /**
     * Saves a post. In addition to `users`, the post will also belong to the
     * authenticated user.
     *
     * @param post the post to be saved
     * @return the saved post
     */
    @PostMapping
    @JsonView(Post.Summary.class)
    @PreAuthorize("isAuthenticated()")
    public Post save(@RequestBody final Post post) {
        final User user = SecurityUtil.getAuthenticatedUser();

        post.setAuthor(user);

        return postService.save(post);
    }

    /**
     * Saves a comment.
     *
     * @param id      the ID of the post the comment will belong to
     * @param comment the comment to be saved
     * @return the saved comment
     */
    @PostMapping(path = "{id}/comment")
    @PreAuthorize("isAuthenticated()")
    public Comment saveComment(@PathVariable("id") final Long id, @RequestBody final Comment comment) {
        final User user = SecurityUtil.getAuthenticatedUser();

        comment.setAuthor(user);

        final Post post = postService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        comment.setPost(post);

        return postService.saveComment(comment);
    }

}