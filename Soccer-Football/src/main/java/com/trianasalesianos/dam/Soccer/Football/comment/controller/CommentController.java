package com.trianasalesianos.dam.Soccer.Football.comment.controller;

import com.trianasalesianos.dam.Soccer.Football.comment.dto.GetCommentDto;
import com.trianasalesianos.dam.Soccer.Football.comment.model.Comment;
import com.trianasalesianos.dam.Soccer.Football.comment.service.CommentService;
import com.trianasalesianos.dam.Soccer.Football.search.util.SearchCriteria;
import com.trianasalesianos.dam.Soccer.Football.search.util.SearchCriteriaExtractor;
import com.trianasalesianos.dam.Soccer.Football.team.dto.GetTeamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public Page<GetCommentDto> getAll(@RequestParam(value = "search", defaultValue = "") String search,
                                      @PageableDefault(size = 15, page = 0, sort = {"uploadDate"}, direction = Sort.Direction.DESC) Pageable pageable) {

        List<SearchCriteria> params = SearchCriteriaExtractor.extractSearchCriteriaList(search);

        return commentService.search(params,pageable).map(GetCommentDto::fromComment);

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
