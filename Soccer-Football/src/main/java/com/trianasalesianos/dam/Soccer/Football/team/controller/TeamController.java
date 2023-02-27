package com.trianasalesianos.dam.Soccer.Football.team.controller;

import com.trianasalesianos.dam.Soccer.Football.comment.model.Comment;
import com.trianasalesianos.dam.Soccer.Football.search.util.SearchCriteria;
import com.trianasalesianos.dam.Soccer.Football.search.util.SearchCriteriaExtractor;
import com.trianasalesianos.dam.Soccer.Football.team.dto.EditTeamDto;
import com.trianasalesianos.dam.Soccer.Football.team.dto.GetTeamDto;
import com.trianasalesianos.dam.Soccer.Football.team.dto.NewTeamDto;
import com.trianasalesianos.dam.Soccer.Football.team.model.Team;
import com.trianasalesianos.dam.Soccer.Football.team.service.TeamService;
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
@RequestMapping("/team")
@Validated
public class TeamController {

    private final TeamService teamService;

    @Operation(summary = "Get a list of Teams with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Teams Found",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Comment.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                               {
                                                   "content": [
                                                       {
                                                           "id": 3,
                                                           "teamName": "Betis"
                                                       },
                                                       {
                                                           "id": 4,
                                                           "teamName": "Betis"
                                                       }
                                                   ],
                                                   "pageable": {
                                                       "sort": {
                                                           "empty": false,
                                                           "sorted": true,
                                                           "unsorted": false
                                                       },
                                                       "offset": 0,
                                                       "pageSize": 15,
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
                    description = "No Teams Found",
                    content = @Content),
    })
    @GetMapping("/")
    public Page<GetTeamDto> getAll(@RequestParam(value = "search", defaultValue = "") String search,
                                   @PageableDefault(size = 15, page = 0) Pageable pageable) {

        List<SearchCriteria> params = SearchCriteriaExtractor.extractSearchCriteriaList(search);

        return teamService.search(params,pageable).map(GetTeamDto::fromTeam);

    }

    @Operation(summary = "Get a Team by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Team Found",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Team.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                  {
                                                       "id": 3,
                                                       "teamName": "Betis"
                                                   }
                                             ]                                         
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No Team Found",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public GetTeamDto getById(@PathVariable Long id) {


        return GetTeamDto.fromTeam(teamService.findById(id).orElse(null));

    }

    @Operation(summary = "Create a Team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Team Created",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Team.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                  {
                                                        "id": 4,
                                                        "teamName": "Betis"
                                                   }
                                             ]                                         
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "No Team Creation Request",
                    content = @Content),
    })
    @PostMapping("/")
    public ResponseEntity<GetTeamDto> createNewTeam(@Valid @RequestBody NewTeamDto newTeamDto) {

        Team created = teamService.save(newTeamDto);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId()).toUri();


        return ResponseEntity
                .created(createdURI)
                .body(GetTeamDto.fromTeam(created));

    }

    @Operation(summary = "Update a teamName of Team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "teamName updated Successfully",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Team.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                    {
                                                         "id": 4,
                                                         "teamName": "Madrid"
                                                     }
                                            ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Bad teamName of Team update Request",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public GetTeamDto editTeam(@PathVariable Long id, @Valid @RequestBody EditTeamDto editTeamDto) {

        Team edited = teamService.editDetails(id, editTeamDto);

        return GetTeamDto.fromTeam(edited);
    }

    @Operation(summary = "Delete Team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Team removed Successfully",
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

        teamService.delete(id);

        return ResponseEntity.noContent().build();

    }

}
