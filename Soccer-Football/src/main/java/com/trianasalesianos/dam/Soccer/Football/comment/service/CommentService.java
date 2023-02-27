package com.trianasalesianos.dam.Soccer.Football.comment.service;

import com.trianasalesianos.dam.Soccer.Football.comment.dto.NewCommentDto;
import com.trianasalesianos.dam.Soccer.Football.comment.model.Comment;
import com.trianasalesianos.dam.Soccer.Football.comment.repository.CommentRepository;
import com.trianasalesianos.dam.Soccer.Football.exception.CommentNotFoundException;
import com.trianasalesianos.dam.Soccer.Football.search.spec.CommentSpecificationBuilder;
import com.trianasalesianos.dam.Soccer.Football.search.util.SearchCriteria;
import com.trianasalesianos.dam.Soccer.Football.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    public Comment save(NewCommentDto comment, User user) {
        return repository.save(
                Comment.builder()
                        .content(comment.getContent())
                        .user(user)
                        .build());
    }

    public Comment edit(Long id, Comment edited) {
        return repository.findById(id)
                .map(note -> {
                    note.setContent(edited.getContent());
                    return repository.save(note);
                })
                .orElseThrow(() -> new CommentNotFoundException());
    }

    public Page<Comment> search(List<SearchCriteria> params, Pageable pageable) {
        CommentSpecificationBuilder commentSpecificationBuilder =
                new CommentSpecificationBuilder(params);
        //GenericSpecificationBuilder<Person> personSpecificationBuilder =
        //        new GenericSpecificationBuilder<>(params, Person.class);
        Specification<Comment> spec =  commentSpecificationBuilder.build();
        return repository.findAll(spec, pageable);
    }

    public void delete(Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
    }

}
