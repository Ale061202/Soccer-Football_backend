package com.trianasalesianos.dam.Soccer.Football.comment.controller;

import com.trianasalesianos.dam.Soccer.Football.comment.dto.GetCommentDto;
import com.trianasalesianos.dam.Soccer.Football.comment.model.Comment;
import com.trianasalesianos.dam.Soccer.Football.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/")
    public List<GetCommentDto> getAll() {

        return commentService.findAll()
                .stream()
                .map(GetCommentDto::fromComment)
                .collect(Collectors.toList());

    }


    @GetMapping("/{id}")
    public GetCommentDto getById(@PathVariable Long id) {


        return GetCommentDto.fromComment(commentService.findById(id));

    }

    @PostMapping("/")
    public ResponseEntity<GetCommentDto> createNewNote(@Valid @RequestBody Comment comment) {

        Comment created = commentService.save(comment);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity
                .created(createdURI)
                .body(GetCommentDto.fromComment(created));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        commentService.delete(id);

        return ResponseEntity.noContent().build();

    }
}
