package com.trianasalesianos.dam.Soccer.Football.league.controller;

import com.trianasalesianos.dam.Soccer.Football.comment.model.Comment;
import com.trianasalesianos.dam.Soccer.Football.league.dto.EditLeagueDto;
import com.trianasalesianos.dam.Soccer.Football.league.dto.GetLeagueDto;
import com.trianasalesianos.dam.Soccer.Football.league.model.League;
import com.trianasalesianos.dam.Soccer.Football.league.service.LeagueService;
import com.trianasalesianos.dam.Soccer.Football.search.util.SearchCriteria;
import com.trianasalesianos.dam.Soccer.Football.search.util.SearchCriteriaExtractor;
import com.trianasalesianos.dam.Soccer.Football.team.dto.GetTeamDto;
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
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/league")
@Validated
public class LeagueController {

    private final LeagueService leagueService;

    private final TeamService teamService;

    @Operation(summary = "Get a list of Leagues with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Leagues Found",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = League.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                  {
                                                        "content": [
                                                            {
                                                                "id": 1,
                                                                "league_name": "La Liga",
                                                                "teams": []
                                                            },
                                                            {
                                                                "id": 2,
                                                                "league_name": "La Liga",
                                                                "teams": []
                                                            }
                                                        ],
                                                        "pageable": {
                                                            "sort": {
                                                                "empty": true,
                                                                "sorted": false,
                                                                "unsorted": true
                                                            },
                                                            "offset": 0,
                                                            "pageNumber": 0,
                                                            "pageSize": 15,
                                                            "paged": true,
                                                            "unpaged": false
                                                        },
                                                        "totalElements": 2,
                                                        "totalPages": 1,
                                                        "last": true,
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
                    description = "No Leagues Found",
                    content = @Content),
    })
    @GetMapping("/")
    public Page<GetLeagueDto> getAll(@RequestParam(value = "search", defaultValue = "") String search,
                                      @PageableDefault(size = 15, page = 0 )Pageable pageable) {

        List<SearchCriteria> params = SearchCriteriaExtractor.extractSearchCriteriaList(search);

        return leagueService.search(params,pageable).map(GetLeagueDto::fromLeague);

    }

    @Operation(summary = "Get a list of Teams from a League")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "League Found",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = League.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                  {
                                                      "id": 1,
                                                      "league_name": "KingsLeague",
                                                      "teams": [
                                                          {
                                                              "id": 3,
                                                              "teamName": "Porcinos FC"
                                                          },
                                                          {
                                                              "id": 4,
                                                              "teamName": "Ultimate Mostoles"
                                                          },
                                                          {
                                                              "id": 5,
                                                              "teamName": "Saiyans FC"
                                                          },
                                                          {
                                                              "id": 6,
                                                              "teamName": "ElBarrio"
                                                          },
                                                          {
                                                              "id": 7,
                                                              "teamName": "PIO"
                                                          },
                                                          {
                                                              "id": 8,
                                                              "teamName": "1K"
                                                          },
                                                          {
                                                              "id": 9,
                                                              "teamName": "Kunisports"
                                                          },
                                                          {
                                                              "id": 10,
                                                              "teamName": "Troncos FC"
                                                          },
                                                          {
                                                              "id": 11,
                                                              "teamName": "Jijantes FC"
                                                          },
                                                          {
                                                              "id": 12,
                                                              "teamName": "Aniquiladores FC"
                                                          },
                                                          {
                                                              "id": 13,
                                                              "teamName": "Xbuyer Team"
                                                          },
                                                          {
                                                              "id": 14,
                                                              "teamName": "Rayo de Barcelona"
                                                          }
                                                      ]
                                                  }
                                             ]                                         
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No League By Id Found",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public GetLeagueDto getById(@PathVariable Long id){
        return GetLeagueDto.fromLeague(leagueService.findById(id).orElse(null));
    }

    @Operation(summary = "Create a League")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "League Created",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = League.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                  {
                                                      "id": 2,
                                                      "league_name": "La Liga",
                                                      "teams": []
                                                  }
                                             ]                                         
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "No League Creation Request",
                    content = @Content),
    })
    @PostMapping("/")
    public ResponseEntity<GetLeagueDto> createNewLeague(@Valid @RequestBody League league) {

        League created = leagueService.save(league);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity
                .created(createdURI)
                .body(GetLeagueDto.fromLeague(created));

    }

    @Operation(summary = "Add a Team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Added a Team to a League",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = League.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                  {
                                                       "id": 2,
                                                       "league_name": "La Liga",
                                                       "teams": [
                                                           {
                                                               "id": 1,
                                                               "teamName": "Betis"
                                                           }
                                                       ]
                                                   }
                                             ]                                         
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "No Team Addition",
                    content = @Content),
    })
    @PostMapping("/{leagueID}/team/{teamId}")
    public ResponseEntity<GetLeagueDto> addTeamLeague(@PathVariable Long leagueID, @PathVariable Long teamId){
        return ResponseEntity.status(HttpStatus.CREATED).body(leagueService.addTeam(leagueID,teamId));
    }

    @Operation(summary = "Update a leagueName of League")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "leagueName updated Successfully",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = League.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                 {
                                                     "id": 1,
                                                     "league_name": "Ligue 1",
                                                     "teams": []
                                                 }                                                    
                                            ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Bad leagueName of League update Request",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public GetLeagueDto editLeague(@PathVariable Long id, @Valid @RequestBody EditLeagueDto editLeagueDto) {

        League edited = leagueService.editDetails(id, editLeagueDto);

        return GetLeagueDto.fromLeague(edited);
    }

    @Operation(summary = "Delete League")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "League removed Successfully",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = League.class)),
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

        leagueService.delete(id);

        return ResponseEntity.noContent().build();

    }
}
