package io.github.pedrominicz.semi.model;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "post", orphanRemoval = true)
    private final List<Comment> comments = Collections.emptyList();

    // Hibernate requires a no-argument constructor.
    public Post() {
        text = null;
    }

    public Post(@JsonProperty("text") final String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    @JsonIgnore
    public User getAuthor() {
        return author;
    }

    public void setAuthor(final User author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

}