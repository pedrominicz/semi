package io.github.pedrominicz.semi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import io.github.pedrominicz.semi.model.Post;
import io.github.pedrominicz.semi.model.PostInterface;

public interface PostRepository extends Repository<Post, Long> {

    @Query("SELECT p.id as id, p.text as text, p.author as author FROM Post p")
    Iterable<PostInterface> findAll();

    Optional<Post> findById(final Long id);

    @Query("SELECT p.id as id, p.text as text, a as author FROM User a JOIN a.posts p WHERE a.id = :id")
    Iterable<PostInterface> findByAuthorId(@Param("id") final Long id);

    Post save(final Post post);

}