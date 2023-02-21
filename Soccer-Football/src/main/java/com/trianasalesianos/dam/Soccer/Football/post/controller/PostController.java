package com.trianasalesianos.dam.Soccer.Football.post.controller;

import com.trianasalesianos.dam.Soccer.Football.post.dto.GetPostDto;
import com.trianasalesianos.dam.Soccer.Football.post.dto.NewPostDto;
import com.trianasalesianos.dam.Soccer.Football.post.model.Post;
import com.trianasalesianos.dam.Soccer.Football.post.service.PostService;
import com.trianasalesianos.dam.Soccer.Football.search.util.SearchCriteria;
import com.trianasalesianos.dam.Soccer.Football.search.util.SearchCriteriaExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
@Validated
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public Page<GetPostDto> getAll(@RequestParam(value = "search", defaultValue = "") String search,
                                   @PageableDefault(size = 20, page = 0) Pageable pageable) {

        List<SearchCriteria> params = SearchCriteriaExtractor.extractSearchCriteriaList(search);

        return postService.search(params,pageable).map(GetPostDto::fromPost);
    }


    @GetMapping("/{id}")
    public GetPostDto getById(@PathVariable Long id) {


        return GetPostDto.fromPost(postService.findById(id));

    }

    @PostMapping("/")
    public ResponseEntity<GetPostDto> createNewNote(@Valid @RequestPart("file") MultipartFile file,
                                                    @RequestPart("post") NewPostDto newPost) {

        Post created = postService.save(newPost,file);

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
