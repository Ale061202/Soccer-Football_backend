package com.trianasalesianos.dam.Soccer.Football.team.controller;

import com.trianasalesianos.dam.Soccer.Football.team.dto.EditTeamDto;
import com.trianasalesianos.dam.Soccer.Football.team.dto.GetTeamDto;
import com.trianasalesianos.dam.Soccer.Football.team.dto.NewTeamDto;
import com.trianasalesianos.dam.Soccer.Football.team.model.Team;
import com.trianasalesianos.dam.Soccer.Football.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/")
    public List<GetTeamDto> getAll() {

        return teamService.findAll()
                .stream()
                .map(GetTeamDto::fromTeam)
                .collect(Collectors.toList());

    }


    @GetMapping("/{id}")
    public GetTeamDto getById(@PathVariable Long id) {


        return GetTeamDto.fromTeam(teamService.findById(id).orElse(null));

    }

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

    @PutMapping("/{id}")
    public GetTeamDto editTeam(@PathVariable Long id, @Valid @RequestBody EditTeamDto editTeamDto) {

        Team edited = teamService.editDetails(id, editTeamDto);

        return GetTeamDto.fromTeam(edited);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        teamService.delete(id);

        return ResponseEntity.noContent().build();

    }

}
