package io.github.pedrominicz.semi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.pedrominicz.semi.model.Comment;
import io.github.pedrominicz.semi.repository.CommentRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment save(final Comment comment) {
        return commentRepository.save(comment);
    }

    public Optional<Comment> findById(final Long id) {
        return commentRepository.findById(id);
    }

    public void deleteById(final Long id) {
        commentRepository.deleteById(id);
    }

}