package io.github.pedrominicz.semi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import io.github.pedrominicz.semi.model.Post;

public interface PostRepository extends CrudRepository<Post, Long> {

    @Query("SELECT p FROM Post p JOIN p.author a WHERE a.username = :username")
    List<Post> findByAuthorUsername(@Param("username") final String username);

    @Query("SELECT p FROM Post p JOIN p.categories c WHERE c.category = :category")
    List<Post> findByCategory(@Param("category") final String category);

}