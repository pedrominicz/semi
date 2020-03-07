package io.github.pedrominicz.semi.model;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id = null;

    @NotNull
    private final String text;

    @ManyToMany
    private Set<User> users = Collections.emptySet();

    @OneToMany
    private List<Comment> comments = Collections.emptyList();

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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(final Set<User> users) {
        this.users = users;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(final List<Comment> comments) {
        this.comments = comments;
    }
}