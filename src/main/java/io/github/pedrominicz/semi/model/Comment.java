package io.github.pedrominicz.semi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id = null;

    @NotNull
    private final String text;

    @ManyToOne
    @NotNull
    private final User author;

    @ManyToOne
    @NotNull
    private final Post post;

    // Hibernate requires a no-argument constructor.
    public Comment() {
        text = null;
        author = null;
        post = null;
    }

    public Comment(final User author, final Post post, @JsonProperty("text") final String text) {
        this.text = text;
        this.author = author;
        this.post = post;
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

    @JsonIgnore
    public Post getPost() {
        return post;
    }

}