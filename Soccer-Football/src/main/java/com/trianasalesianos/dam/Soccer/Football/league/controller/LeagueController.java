package com.trianasalesianos.dam.Soccer.Football.league.controller;

import com.trianasalesianos.dam.Soccer.Football.league.dto.EditLeagueDto;
import com.trianasalesianos.dam.Soccer.Football.league.dto.GetLeagueDto;
import com.trianasalesianos.dam.Soccer.Football.league.model.League;
import com.trianasalesianos.dam.Soccer.Football.league.service.LeagueService;
import com.trianasalesianos.dam.Soccer.Football.team.model.Team;
import com.trianasalesianos.dam.Soccer.Football.team.service.TeamService;
import lombok.RequiredArgsConstructor;
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
    public List<GetLeagueDto> getAll() {

        return leagueService.findAll()
                .stream()
                .map(GetLeagueDto::fromLeague)
                .collect(Collectors.toList());

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
    public ResponseEntity<League> addTeamLeague(@PathVariable Long id, @PathVariable Long id2){
        Optional<League> l = leagueService.findById(id);
        if (l.isPresent()){
            League league = l.get();
            Optional<Team> t = teamService.findById(id2);
            if (t.isPresent()){
                league.addTeam(t.get());
                leagueService.save(league);
                return ResponseEntity.ok(league);
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
