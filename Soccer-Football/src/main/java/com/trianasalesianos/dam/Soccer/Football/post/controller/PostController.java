package com.trianasalesianos.dam.Soccer.Football.post.controller;

import com.trianasalesianos.dam.Soccer.Football.post.dto.GetPostDto;
import com.trianasalesianos.dam.Soccer.Football.post.dto.NewPostDto;
import com.trianasalesianos.dam.Soccer.Football.post.model.Post;
import com.trianasalesianos.dam.Soccer.Football.post.service.PostService;
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
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public List<GetPostDto> getAll() {

        return postService.findAll()
                .stream()
                .map(GetPostDto::fromPost)
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public GetPostDto getById(@PathVariable Long id) {


        return GetPostDto.fromPost(postService.findById(id));

    }

    @PostMapping("/")
    public ResponseEntity<GetPostDto> createNewNote(@Valid @RequestBody NewPostDto newPostDto) {

        Post created = postService.save(newPostDto);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity
                .created(createdURI)
                .body(GetPostDto.fromPost(created));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        postService.delete(id);

        return ResponseEntity.noContent().build();

    }
}
