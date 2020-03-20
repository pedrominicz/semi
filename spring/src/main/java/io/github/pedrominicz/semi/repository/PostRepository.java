package io.github.pedrominicz.semi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import io.github.pedrominicz.semi.model.Post;

public interface PostRepository extends CrudRepository<Post, Long> {

    @Query("SELECT p FROM User u JOIN u.posts p WHERE u.id = :id")
    List<Post> findByAuthorId(@Param("id") final Long id);

}