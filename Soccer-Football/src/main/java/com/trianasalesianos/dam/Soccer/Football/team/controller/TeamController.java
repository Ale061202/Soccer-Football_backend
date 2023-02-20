package com.trianasalesianos.dam.Soccer.Football.team.controller;

import com.trianasalesianos.dam.Soccer.Football.search.util.SearchCriteria;
import com.trianasalesianos.dam.Soccer.Football.search.util.SearchCriteriaExtractor;
import com.trianasalesianos.dam.Soccer.Football.team.dto.EditTeamDto;
import com.trianasalesianos.dam.Soccer.Football.team.dto.GetTeamDto;
import com.trianasalesianos.dam.Soccer.Football.team.dto.NewTeamDto;
import com.trianasalesianos.dam.Soccer.Football.team.model.Team;
import com.trianasalesianos.dam.Soccer.Football.team.service.TeamService;
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

    @GetMapping("/")
    public Page<GetTeamDto> getAll(@RequestParam(value = "search", defaultValue = "") String search,
                                   @PageableDefault(size = 15, page = 0, sort = {"uploadDate"}, direction = Sort.Direction.DESC) Pageable pageable) {

        List<SearchCriteria> params = SearchCriteriaExtractor.extractSearchCriteriaList(search);

        return teamService.search(params,pageable).map(GetTeamDto::fromTeam);

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
