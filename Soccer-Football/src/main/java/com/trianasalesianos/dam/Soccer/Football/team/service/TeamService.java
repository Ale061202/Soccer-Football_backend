package com.trianasalesianos.dam.Soccer.Football.team.service;

import com.trianasalesianos.dam.Soccer.Football.exception.PostNotFoundException;
import com.trianasalesianos.dam.Soccer.Football.exception.TeamNotFoundException;
import com.trianasalesianos.dam.Soccer.Football.post.model.Post;
import com.trianasalesianos.dam.Soccer.Football.team.model.Team;
import com.trianasalesianos.dam.Soccer.Football.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository repository;


    public List<Team> findAll() {

        List<Team> result = repository.findAll();

        if (result.isEmpty())
            throw new EntityNotFoundException("No users with this search criteria");

        return repository.findAll();
    }

    public Team findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user with id: " + id));

    }

    public Team save(Team team) {
        return repository.save(team);
    }

    public Team edit(Long id, Team edited) {
        return repository.findById(id)
                .map(note -> {
                    note.setName(edited.getName());
                    return repository.save(note);
                })
                .orElseThrow(() -> new TeamNotFoundException());
    }

    public void delete(Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
    }

}
