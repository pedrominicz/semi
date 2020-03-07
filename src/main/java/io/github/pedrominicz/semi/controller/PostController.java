package io.github.pedrominicz.semi.controller;

import java.util.Set;
import java.util.stream.Collectors;

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
        final Post post = postService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        post.setComments(commentService.findAllByPostId(id));

        return post;
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

        final Set<String> usernames = post.getUsers().stream().map(User::getUsername).collect(Collectors.toSet());

        usernames.add(user.getUsername());

        post.setUsers(userService.findByUsernameIn(usernames));

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

        final Post post = postService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return commentService.save(new Comment(comment.getText(), user, post));
    }

    /**
     * Deletes a comment if it exists and the user is authorized to do so.
     *
     * @param id         the ID of the post the comment belongs to
     * @param comment_id the comment ID
     */
    @DeleteMapping(path = "{id}/comment/{comment_id}")
    @PreAuthorize("isAuthenticated()")
    public void deleteCommentById(@PathVariable("id") final Long id,
            @PathVariable("comment_id") final Long comment_id) {
        // Verity if the comment exists and belongs to the post.
        final Comment comment = commentService.findById(comment_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        final Post post = comment.getPost();

        if (!post.getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        // Verify if the authenticated user is authorized to delete the comment.
        final Set<User> authorizedUsers = post.getUsers();

        authorizedUsers.add(comment.getUser());

        if (!authorizedUsers.contains(SecurityUtil.getAuthenticatedUser())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        commentService.deleteById(comment_id);
    }

}