package com.trianasalesianos.dam.Soccer.Football.league.controller;

import com.trianasalesianos.dam.Soccer.Football.league.model.League;
import com.trianasalesianos.dam.Soccer.Football.league.service.LeagueService;
import com.trianasalesianos.dam.Soccer.Football.post.model.Post;
import com.trianasalesianos.dam.Soccer.Football.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LeagueController {

    private final LeagueService leagueService;

    @GetMapping("/")
    public List<League> getAll() {

        return leagueService.findAll();

    }


    @GetMapping("/{id}")
    public League getById(@PathVariable Long id) {


        return leagueService.findById(id);

    }

    @PostMapping("/")
    public ResponseEntity<League> createNewNote(@Valid @RequestBody League league) {

        League created = leagueService.save(league);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity
                .created(createdURI)
                .body(created);

    }

    @PutMapping("/{id}")
    public League edit(@PathVariable Long id, @RequestBody League edited) {
        return leagueService.edit(id, edited);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        leagueService.delete(id);

        return ResponseEntity.noContent().build();

    }
}
