package com.trianasalesianos.dam.Soccer.Football.league.controller;

import com.trianasalesianos.dam.Soccer.Football.comment.dto.GetCommentDto;
import com.trianasalesianos.dam.Soccer.Football.league.dto.EditLeagueDto;
import com.trianasalesianos.dam.Soccer.Football.league.dto.GetLeagueDto;
import com.trianasalesianos.dam.Soccer.Football.league.model.League;
import com.trianasalesianos.dam.Soccer.Football.league.service.LeagueService;
import com.trianasalesianos.dam.Soccer.Football.search.util.SearchCriteria;
import com.trianasalesianos.dam.Soccer.Football.search.util.SearchCriteriaExtractor;
import com.trianasalesianos.dam.Soccer.Football.team.model.Team;
import com.trianasalesianos.dam.Soccer.Football.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/league")
@Validated
public class LeagueController {

    private final LeagueService leagueService;

    private final TeamService teamService;

    @GetMapping("/")
    public Page<GetLeagueDto> getAll(@RequestParam(value = "search", defaultValue = "") String search,
                                      @PageableDefault(size = 15, page = 0 )Pageable pageable) {

        List<SearchCriteria> params = SearchCriteriaExtractor.extractSearchCriteriaList(search);

        return leagueService.search(params,pageable).map(GetLeagueDto::fromLeague);

    }

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

    @PostMapping("/league/{id}/team/{id2}")
    public ResponseEntity<GetLeagueDto> addTeamLeague(@PathVariable Long id, @PathVariable Long id2){
        Optional<League> l = leagueService.findById(id);
        if (!l.isEmpty()){
            League league = l.get();
            Optional<Team> t = teamService.findById(id2);
            if (t.isPresent()){
                league.addTeam(t.get());
                leagueService.save(league);
                return ResponseEntity.ok(GetLeagueDto.fromLeague(league));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{id}")
    public GetLeagueDto editLeague(@PathVariable Long id, @Valid @RequestBody EditLeagueDto editLeagueDto) {

        League edited = leagueService.editDetails(id, editLeagueDto);

        return GetLeagueDto.fromLeague(edited);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        leagueService.delete(id);

        return ResponseEntity.noContent().build();

    }
}
