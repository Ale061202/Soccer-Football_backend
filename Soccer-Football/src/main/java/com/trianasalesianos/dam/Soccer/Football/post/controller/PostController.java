package com.trianasalesianos.dam.Soccer.Football.post.controller;

import com.trianasalesianos.dam.Soccer.Football.comment.model.Comment;
import com.trianasalesianos.dam.Soccer.Football.post.dto.GetPostDto;
import com.trianasalesianos.dam.Soccer.Football.post.dto.NewPostDto;
import com.trianasalesianos.dam.Soccer.Football.post.model.Post;
import com.trianasalesianos.dam.Soccer.Football.post.service.PostService;
import com.trianasalesianos.dam.Soccer.Football.search.util.SearchCriteria;
import com.trianasalesianos.dam.Soccer.Football.search.util.SearchCriteriaExtractor;
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
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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

    @Operation(summary = "Get a list of Posts with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Posts Found",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Post.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                  {
                                                        "content": [
                                                            {
                                                                "id": 1,
                                                                "image": null,
                                                                "title": "Nueva imagen"
                                                            },
                                                            {
                                                                "id": 2,
                                                                "image": null,
                                                                "title": "Nueva imagen"
                                                            }
                                                        ],
                                                        "pageable": {
                                                            "sort": {
                                                                "empty": true,
                                                                "sorted": false,
                                                                "unsorted": true
                                                            },
                                                            "offset": 0,
                                                            "pageSize": 20,
                                                            "pageNumber": 0,
                                                            "paged": true,
                                                            "unpaged": false
                                                        },
                                                        "last": true,
                                                        "totalElements": 2,
                                                        "totalPages": 1,
                                                        "size": 15,
                                                        "number": 0,
                                                        "sort": {
                                                            "empty": true,
                                                            "sorted": false,
                                                            "unsorted": true
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
                    description = "No Posts Found",
                    content = @Content),
    })
    @GetMapping("/")
    public Page<GetPostDto> getAll(@RequestParam(value = "search", defaultValue = "") String search,
                                   @PageableDefault(size = 15, page = 0) Pageable pageable) {

        List<SearchCriteria> params = SearchCriteriaExtractor.extractSearchCriteriaList(search);

        return postService.search(params,pageable).map(GetPostDto::fromPost);
    }

    @Operation(summary = "Get a Post by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Post Found",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Post.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                  {
                                                    "id": 2,
                                                    "image": null,
                                                    "title": "Nueva imagen"
                                                  }
                                             ]                                         
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No Post Found",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public GetPostDto getById(@PathVariable Long id) {


        return GetPostDto.fromPost(postService.findById(id));

    }

    @Operation(summary = "Create a Post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Post Created",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Post.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                  {
                                                       "id": 2,
                                                       "image": null,
                                                       "title": "Nueva imagen"
                                                   }
                                             ]                                         
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "No Post Creation Request",
                    content = @Content),
    })
    @PostMapping("/")
    public ResponseEntity<GetPostDto> createNewNote(@Valid @RequestBody NewPostDto newPost) {

        Post created = postService.save(newPost);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity
                .created(createdURI)
                .body(GetPostDto.fromPost(created));

    }

    /*@PostMapping("/")
    public ResponseEntity<Post> create(
            @RequestPart("file") MultipartFile file,
            @RequestPart("post") NewPostDto newPost
    ) {
        Post post = postService.save(newPost,file);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        postService.delete(id);

        return ResponseEntity.noContent().build();

    }*/
}
