package io.github.pedrominicz.semi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

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
    private final Post post;

    public Comment(@JsonProperty("text") final String text, final Post post) {
        this.text = text;
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}