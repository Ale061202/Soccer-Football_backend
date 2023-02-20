package com.trianasalesianos.dam.Soccer.Football.league.controller;

import com.trianasalesianos.dam.Soccer.Football.league.dto.EditLeagueDto;
import com.trianasalesianos.dam.Soccer.Football.league.dto.GetLeagueDto;
import com.trianasalesianos.dam.Soccer.Football.league.model.League;
import com.trianasalesianos.dam.Soccer.Football.league.service.LeagueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/league")
@Validated
public class LeagueController {

    private final LeagueService leagueService;

    @GetMapping("/")
    public List<GetLeagueDto> getAll() {

        return leagueService.findAll()
                .stream()
                .map(GetLeagueDto::fromLeague)
                .collect(Collectors.toList());

    }


    @GetMapping("/{id}")
    public GetLeagueDto getById(@PathVariable Long id) {


        return GetLeagueDto.fromLeague(leagueService.findById(id));

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
