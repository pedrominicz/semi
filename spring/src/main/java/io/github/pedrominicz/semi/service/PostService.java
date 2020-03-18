package io.github.pedrominicz.semi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.pedrominicz.semi.model.Comment;
import io.github.pedrominicz.semi.model.Post;
import io.github.pedrominicz.semi.model.PostInterface;
import io.github.pedrominicz.semi.model.User;
import io.github.pedrominicz.semi.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostRepository postRepository;


    /**
     * Returns all posts.
     *
     * @return the posts
     */
    public Iterable<PostInterface> findAll() {
        return postRepository.findAll();
    }

    /**
     * Returns a post and all comments that belong to it.
     *
     * @param id the ID of the post
     * @return the post
     */
    public Optional<Post> findById(final Long id) {
        return postRepository.findById(id);
    }

    /**
     * Returns all posts by a given user.
     *
     * @param id the ID of the user
     * @return the posts by the user
     */
    public Iterable<PostInterface> findByAuthorId(final Long id) {
        return postRepository.findByAuthorId(id);
    }

    /**
     * Saves a post. In addition to `users`, the post will also belong to the
     * authenticated user.
     *
     * @param post the post to be saved
     * @return the saved post
     */
    public Post save(final Post post) {
        return postRepository.save(post);
    }

    /**
     * Saves a comment.
     *
     * @param comment the comment to be saved
     * @return the saved comment
     */
	public Comment saveComment(final Comment comment) {
		return commentService.save(comment);
	}

    public void deleteCommentById(final User user, final Long id, final Long commentId) {
        // Empty.
    }

}