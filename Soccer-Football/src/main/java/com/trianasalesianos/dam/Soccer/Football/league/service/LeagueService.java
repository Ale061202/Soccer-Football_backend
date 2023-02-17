package com.trianasalesianos.dam.Soccer.Football.league.service;

import com.trianasalesianos.dam.Soccer.Football.exception.LeagueNotFoundException;
import com.trianasalesianos.dam.Soccer.Football.exception.PostNotFoundException;
import com.trianasalesianos.dam.Soccer.Football.league.model.League;
import com.trianasalesianos.dam.Soccer.Football.league.repository.LeagueRepository;
import com.trianasalesianos.dam.Soccer.Football.post.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeagueService {
    private final LeagueRepository repository;


    public List<League> findAll() {

        List<League> result = repository.findAll();

        if (result.isEmpty())
            throw new EntityNotFoundException("No users with this search criteria");

        return repository.findAll();
    }

    public League findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user with id: " + id));

    }

    public League save(League league) {
        return repository.save(league);
    }

    public League edit(Long id, League edited) {
        return repository.findById(id)
                .map(note -> {
                    note.setLeague_name(edited.getLeague_name());
                    return repository.save(note);
                })
                .orElseThrow(() -> new LeagueNotFoundException());
    }

    public void delete(Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
    }

}
