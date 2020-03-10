package io.github.pedrominicz.semi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
        return postService.findByIdWithComments(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Saves a post. In addition to `users`, the post will also belong to the
     * authenticated user.
     *
     * @param post the post to be saved
     * @return the saved post
     */
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public Post save(@RequestBody final Post post) {
        final User user = SecurityUtil.getAuthenticatedUser();

        return postService.save(user, post);
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

        final Post post = postService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return postService.saveComment(user, post, comment);
    }

    /**
     * Deletes a comment if it exists and the user is authorized to do so.
     *
     * @param id        the ID of the post the comment belongs to
     * @param commentId the comment ID
     */
    @DeleteMapping(path = "{id}/comment/{commentId}")
    @PreAuthorize("isAuthenticated()")
    public void deleteCommentById(@PathVariable("id") final Long id, @PathVariable("commentId") final Long commentId) {
        final User user = SecurityUtil.getAuthenticatedUser();

        if (!postService.deleteCommentById(user, id, commentId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

}