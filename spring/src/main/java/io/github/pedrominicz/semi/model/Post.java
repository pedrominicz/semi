package io.github.pedrominicz.semi.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id = null;

    @NotNull
    private final String text;

    @JoinColumn(name = "author_id")
    @ManyToOne
    @NotNull
    private User author = null;

    @ManyToMany
    private List<Category> categories;

    // Hibernate requires a no-argument constructor.
    public Post() {
        text = null;
    }

    public Post(@JsonProperty("text") final String text, @JsonProperty("categories") final List<Category> categories) {
        this.text = text;
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(final User author) {
        this.author = author;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(final List<Category> categories) {
        this.categories = categories;
    }

}