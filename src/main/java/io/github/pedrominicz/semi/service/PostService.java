package io.github.pedrominicz.semi.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.pedrominicz.semi.model.Comment;
import io.github.pedrominicz.semi.model.Post;
import io.github.pedrominicz.semi.model.User;
import io.github.pedrominicz.semi.repository.PostRepository;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    public Post save(final User user, final Post post) {
        final Set<String> usernames = post.getModerators().stream().map(User::getUsername).collect(Collectors.toSet());

        usernames.add(user.getUsername());

        post.setModerators(userService.findByUsernameIn(usernames));

        return postRepository.save(post);
    }

    public Comment saveComment(final User user, final Post post, final Comment comment) {
        return commentService.save(new Comment(user, post, comment.getText()));
    }

    public Optional<Post> findById(final Long id) {
        return postRepository.findById(id);
    }

    public Optional<Post> findByIdWithComments(final Long id) {
        final List<Comment> comments = commentService.findAllByPostId(id);

        final Optional<Post> post = findById(id);

        post.ifPresent(post_ -> post_.setComments(comments));

        return post;
    }

    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

	public Boolean deleteCommentById(final User user, final Long id, final Long commentId) {
        // Verity if the comment exists and belongs to the post.
        final Comment comment = commentService.findById(commentId).get();

        final Post post = comment.getPost();

        if (!post.getId().equals(id)) {
            return false;
        }

        // Verify if the authenticated user is authorized to delete the comment.
        final Set<User> moderators = post.getModerators();

        moderators.add(comment.getAuthor());

        if (!moderators.contains(user)) {
            return false;
        }

        commentService.deleteById(commentId);

        return true;
	}

}