package io.github.pedrominicz.semi.model;

import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id = null;

    @Column(length = 16, unique = true)
    @NotNull
    private final String name;

    @ManyToMany(mappedBy = "categories")
    private final List<Post> posts = Collections.emptyList();

    // Hibernate requires a no-argument constructor.
    public Category() {
        name = null;
    }

    public Category(@JsonProperty("name") final String name) {
        this.name = name;
    }

    @JsonView(View.class)
    public String getName() {
        return name;
    }

    public interface View {

        // Empty.

    }

}