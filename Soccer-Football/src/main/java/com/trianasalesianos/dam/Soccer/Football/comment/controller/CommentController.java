package com.trianasalesianos.dam.Soccer.Football.comment.controller;

import com.trianasalesianos.dam.Soccer.Football.comment.dto.GetCommentDto;
import com.trianasalesianos.dam.Soccer.Football.comment.model.Comment;
import com.trianasalesianos.dam.Soccer.Football.comment.service.CommentService;
import com.trianasalesianos.dam.Soccer.Football.search.util.SearchCriteria;
import com.trianasalesianos.dam.Soccer.Football.search.util.SearchCriteriaExtractor;
import com.trianasalesianos.dam.Soccer.Football.team.dto.GetTeamDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
@Validated
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "Get a list of Comments with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Comments Found",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Comment.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                  {
                                                       "content": [
                                                           {
                                                               "id": 5,
                                                               "content": "Este es el nuevo contenido"
                                                           },
                                                           {
                                                               "id": 4,
                                                               "content": "Este es el nuevo contenido"
                                                           }
                                                       ],
                                                       "pageable": {
                                                           "sort": {
                                                               "empty": false,
                                                               "sorted": true,
                                                               "unsorted": false
                                                           },
                                                           "offset": 0,
                                                           "pageNumber": 0,
                                                           "pageSize": 15,
                                                           "unpaged": false,
                                                           "paged": true
                                                       },
                                                       "last": true,
                                                       "totalPages": 1,
                                                       "totalElements": 2,
                                                       "size": 15,
                                                       "number": 0,
                                                       "sort": {
                                                           "empty": false,
                                                           "sorted": true,
                                                           "unsorted": false
                                                       },
                                                       "first": true,
                                                       "numberOfElements": 2,
                                                       "empty": false
                                                   }
                                             ]                                         
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No Comments Found",
                    content = @Content),
    })
    @GetMapping("/")
    public Page<GetCommentDto> getAll(@RequestParam(value = "search", defaultValue = "") String search,
                                      @PageableDefault(size = 15, page = 0, sort = {"uploadDate"}, direction = Sort.Direction.DESC) Pageable pageable) {

        List<SearchCriteria> params = SearchCriteriaExtractor.extractSearchCriteriaList(search);

        return commentService.search(params,pageable).map(GetCommentDto::fromComment);

    }

    @Operation(summary = "Get a Comment by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Comment Found",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Comment.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                  {
                                                      "id": 4,
                                                      "content": "Este es el nuevo contenido"
                                                  }
                                             ]                                         
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No Comment Found",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public GetCommentDto getById(@PathVariable Long id) {


        return GetCommentDto.fromComment(commentService.findById(id));

    }

    @Operation(summary = "Create a Comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Comment Created",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Comment.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                  {
                                                       "id": 6,
                                                       "content": "Este es el nuevo contenido"
                                                   }
                                             ]                                         
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "No Comment Creation Request",
                    content = @Content),
    })
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

    @Operation(summary = "Delete Comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Comment removed Successfully",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Comment.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                
                                            }                                        
                                            """
                            )}
                    )}),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        commentService.delete(id);

        return ResponseEntity.noContent().build();

    }
}
