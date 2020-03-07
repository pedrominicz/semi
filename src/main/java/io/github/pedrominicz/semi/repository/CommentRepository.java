package io.github.pedrominicz.semi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.github.pedrominicz.semi.model.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findAllByPostId(final Long post_id);
}