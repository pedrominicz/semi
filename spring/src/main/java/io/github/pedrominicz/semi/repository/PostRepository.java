package io.github.pedrominicz.semi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import io.github.pedrominicz.semi.model.Post;

public interface PostRepository extends CrudRepository<Post, Long> {

    @Query("SELECT p FROM Post p JOIN p.author a WHERE a.name = :name")
    List<Post> findByAuthor(@Param("name") final String name);

    @Query("SELECT p FROM Post p JOIN p.categories c WHERE c.name = :name")
    List<Post> findByCategory(@Param("name") final String name);

    @Query("SELECT p FROM Post p JOIN p.author a JOIN p.categories c WHERE a.name = :author AND c.name = :category")
    List<Post> findByAuthorAndCategory(@Param("author") final String author, @Param("category") final String category);

}