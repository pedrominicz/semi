package io.github.pedrominicz.semi.repository;

import org.springframework.data.repository.CrudRepository;

import io.github.pedrominicz.semi.model.Post;

public interface PostRepository extends CrudRepository<Post, Long> {
    // Empty.
}