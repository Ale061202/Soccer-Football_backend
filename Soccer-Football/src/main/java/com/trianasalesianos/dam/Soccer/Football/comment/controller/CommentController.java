package com.trianasalesianos.dam.Soccer.Football.comment.controller;

import com.trianasalesianos.dam.Soccer.Football.comment.model.Comment;
import com.trianasalesianos.dam.Soccer.Football.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/")
    public List<Comment> getAll() {

        return commentService.findAll();

    }


    @GetMapping("/{id}")
    public Comment getById(@PathVariable Long id) {


        return commentService.findById(id);

    }

    @PostMapping("/")
    public ResponseEntity<Comment> createNewNote(@Valid @RequestBody Comment comment) {

        Comment created = commentService.save(comment);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity
                .created(createdURI)
                .body(created);

    }

    @PutMapping("/{id}")
    public Comment edit(@PathVariable Long id, @RequestBody Comment edited) {
        return commentService.edit(id, edited);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        commentService.delete(id);

        return ResponseEntity.noContent().build();

    }
}
