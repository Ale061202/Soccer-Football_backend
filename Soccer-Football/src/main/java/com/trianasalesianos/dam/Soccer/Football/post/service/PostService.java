package com.trianasalesianos.dam.Soccer.Football.post.service;


import com.trianasalesianos.dam.Soccer.Football.exception.PostNotFoundException;
import com.trianasalesianos.dam.Soccer.Football.post.model.Post;
import com.trianasalesianos.dam.Soccer.Football.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository repository;


    public List<Post> findAll() {

        List<Post> result = repository.findAll();

        if (result.isEmpty())
            throw new EntityNotFoundException("No users with this search criteria");

        return repository.findAll();
    }

    public Post findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user with id: " + id));

    }

    public Post save(Post post) {
        return repository.save(post);
    }

    public Post edit(Long id, Post edited) {
        return repository.findById(id)
                .map(note -> {
                    note.setTitle(edited.getTitle());
                    note.setContent(edited.getContent());
                    return repository.save(note);
                })
                .orElseThrow(() -> new PostNotFoundException());
    }

    public void delete(Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
    }

}
