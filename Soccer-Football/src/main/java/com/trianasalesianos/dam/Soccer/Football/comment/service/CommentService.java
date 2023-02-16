package com.trianasalesianos.dam.Soccer.Football.comment.service;

import com.trianasalesianos.dam.Soccer.Football.comment.model.Comment;
import com.trianasalesianos.dam.Soccer.Football.comment.repository.CommentRepository;
import com.trianasalesianos.dam.Soccer.Football.exception.CommentNotFoundException;
import com.trianasalesianos.dam.Soccer.Football.exception.PostNotFoundException;
import com.trianasalesianos.dam.Soccer.Football.post.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository repository;


    public List<Comment> findAll() {

        List<Comment> result = repository.findAll();

        if (result.isEmpty())
            throw new EntityNotFoundException("No users with this search criteria");

        return repository.findAll();
    }

    public Comment findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user with id: " + id));

    }

    public Comment save(Comment comment) {
        return repository.save(comment);
    }

    public Comment edit(Long id, Comment edited) {
        return repository.findById(id)
                .map(note -> {
                    note.setContent(edited.getContent());
                    return repository.save(note);
                })
                .orElseThrow(() -> new CommentNotFoundException());
    }

    public void delete(Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
    }

}
