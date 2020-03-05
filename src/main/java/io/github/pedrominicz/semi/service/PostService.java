package io.github.pedrominicz.semi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.pedrominicz.semi.model.Post;
import io.github.pedrominicz.semi.repository.PostRepository;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post save(final Post post) {
        return postRepository.save(post);
    }

    public Optional<Post> findById(final Long id) {
        return postRepository.findById(id);
    }

    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }
}