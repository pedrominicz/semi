package io.github.pedrominicz.semi.repository;

import org.springframework.data.repository.CrudRepository;

import io.github.pedrominicz.semi.model.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    Iterable<Comment> findAllByPostId(final Long post_id);
}