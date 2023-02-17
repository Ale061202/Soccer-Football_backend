package com.trianasalesianos.dam.Soccer.Football.team.controller;

import com.trianasalesianos.dam.Soccer.Football.post.model.Post;
import com.trianasalesianos.dam.Soccer.Football.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/team")
public class TeamController {

    private final PostService postService;

    @GetMapping("/")
    public List<Post> getAll() {

        return postService.findAll();

    }


    @GetMapping("/{id}")
    public Post getById(@PathVariable Long id) {


        return postService.findById(id);

    }

    @PostMapping("/")
    public ResponseEntity<Post> createNewNote(@Valid @RequestBody Post post) {

        Post created = postService.save(post);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity
                .created(createdURI)
                .body(created);

    }

    @PutMapping("/{id}")
    public Post edit(@PathVariable Long id, @RequestBody Post edited) {
        return postService.edit(id, edited);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        postService.delete(id);

        return ResponseEntity.noContent().build();

    }
}
