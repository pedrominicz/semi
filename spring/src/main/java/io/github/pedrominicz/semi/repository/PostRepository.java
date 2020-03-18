package io.github.pedrominicz.semi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import io.github.pedrominicz.semi.model.Post;
import io.github.pedrominicz.semi.model.PostInterface;

public interface PostRepository extends Repository<Post, Long> {

    @Query("SELECT p.id as id, p.text as text, p.author as author FROM Post p")
    Iterable<PostInterface> findAll();

    Optional<Post> findById(final Long id);

    Post save(final Post post);

}