package com.trianasalesianos.dam.Soccer.Football.post.controller;

import com.trianasalesianos.dam.Soccer.Football.comment.dto.GetCommentDto;
import com.trianasalesianos.dam.Soccer.Football.comment.model.Comment;
import com.trianasalesianos.dam.Soccer.Football.league.dto.GetLeagueDto;
import com.trianasalesianos.dam.Soccer.Football.league.model.League;
import com.trianasalesianos.dam.Soccer.Football.post.dto.GetPostDto;
import com.trianasalesianos.dam.Soccer.Football.post.dto.NewPostDto;
import com.trianasalesianos.dam.Soccer.Football.post.model.Post;
import com.trianasalesianos.dam.Soccer.Football.post.service.PostService;
import com.trianasalesianos.dam.Soccer.Football.search.util.SearchCriteria;
import com.trianasalesianos.dam.Soccer.Football.search.util.SearchCriteriaExtractor;
import com.trianasalesianos.dam.Soccer.Football.user.model.User;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
                                                              "id": 34,
                                                              "image": "logo_922412.png",
                                                              "title": "Nuevo Content",
                                                              "author": "ale",
                                                              "comments": [
                                                                  {
                                                                      "id": 35,
                                                                      "content": "Este es el nuevo contenido",
                                                                      "author": "ale"
                                                                  },
                                                                  {
                                                                      "id": 36,
                                                                      "content": "Este es el nuevo contenido",
                                                                      "author": "ale"
                                                                  }
                                                              ]
                                                          }
                                                      ],
                                                      "pageable": {
                                                          "sort": {
                                                              "empty": true,
                                                              "sorted": false,
                                                              "unsorted": true
                                                          },
                                                          "offset": 0,
                                                          "pageSize": 15,
                                                          "pageNumber": 0,
                                                          "unpaged": false,
                                                          "paged": true
                                                      },
                                                      "last": true,
                                                      "totalElements": 1,
                                                      "totalPages": 1,
                                                      "size": 15,
                                                      "number": 0,
                                                      "sort": {
                                                          "empty": true,
                                                          "sorted": false,
                                                          "unsorted": true
                                                      },
                                                      "first": true,
                                                      "numberOfElements": 1,
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
                                                      "id": 34,
                                                      "image": "logo_922412.png",
                                                      "title": "Nuevo Content",
                                                      "author": "ale",
                                                      "comments": []
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
                                                      "id": 34,
                                                      "image": "logo_922412.png",
                                                      "title": "Nuevo Content",
                                                      "author": "ale",
                                                      "comments": []
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
    public ResponseEntity<GetPostDto> create(@RequestPart("file") MultipartFile file, @RequestPart("post") NewPostDto newPost, @AuthenticationPrincipal User user) {
        GetPostDto post = postService.save(newPost,file,user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(post);
    }
    @Operation(summary = "Add a Comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Added a Comment to a Post",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Post.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                  {
                                                      "id": 34,
                                                      "image": "logo_922412.png",
                                                      "title": "Nuevo Content",
                                                      "author": "ale",
                                                      "comments": []
                                                  }
                                             ]                                         
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "No Comment Addition",
                    content = @Content),
    })

    @PostMapping("/{postId}/comment/{commentId}")
    public ResponseEntity<GetPostDto> addCommentToPost(@PathVariable Long postId, @PathVariable Long commentId){
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.addTeam(postId,commentId));
    }
}
