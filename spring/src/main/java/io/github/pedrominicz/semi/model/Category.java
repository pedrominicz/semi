package io.github.pedrominicz.semi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id = null;

    @Column(length = 16, unique = true)
    @NotNull
    private final String category;

    @ManyToMany(mappedBy = "categories")
    private final List<Post> posts = null;

    // Hibernate requires a no-argument constructor.
    public Category() {
        category = null;
    }

    public Category(@JsonProperty("category") final String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    @JsonIgnore
    public List<Post> getPosts() {
        return posts;
    }

}