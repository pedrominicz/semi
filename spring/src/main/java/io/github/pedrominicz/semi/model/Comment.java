package io.github.pedrominicz.semi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

    @JoinColumn(name = "author_id")
    @ManyToOne
    @NotNull
    private User author = null;

    @JoinColumn(name = "post_id")
    @ManyToOne
    @NotNull
    private Post post = null;

    // Hibernate requires a no-argument constructor.
    public Comment() {
        text = null;
    }

    public Comment(@JsonProperty("text") final String text) {
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

    @JsonIgnore
    public Post getPost() {
        return post;
    }

    public void setPost(final Post post) {
        this.post = post;
    }

}