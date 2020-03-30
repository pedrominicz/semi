package io.github.pedrominicz.semi.model;

import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

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

    @JoinTable(name = "post_category", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    @ManyToMany
    private List<Category> categories = Collections.emptyList();

    // Hibernate requires a no-argument constructor.
    public Post() {
        text = null;
    }

    public Post(@JsonProperty("text") final String text, @JsonProperty("categories") final List<Category> categories) {
        this.text = text;

        if (categories != null) {
            this.categories = categories;
        }
    }

    @JsonView(View.class)
    public Long getId() {
        return id;
    }

    @JsonView(View.class)
    public String getText() {
        return text;
    }

    @JsonView(View.class)
    public User getAuthor() {
        return author;
    }

    public void setAuthor(final User author) {
        this.author = author;
    }

    @JsonView(View.class)
    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(final List<Category> categories) {
        this.categories = categories;
    }

    public interface View extends Category.View, User.View {

        // Empty.

    }

}