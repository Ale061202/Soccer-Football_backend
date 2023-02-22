package com.trianasalesianos.dam.Soccer.Football.team.service;

import com.trianasalesianos.dam.Soccer.Football.search.spec.TeamSpecificationBuilder;
import com.trianasalesianos.dam.Soccer.Football.search.util.SearchCriteria;
import com.trianasalesianos.dam.Soccer.Football.team.dto.EditTeamDto;
import com.trianasalesianos.dam.Soccer.Football.team.dto.NewTeamDto;
import com.trianasalesianos.dam.Soccer.Football.team.model.Team;
import com.trianasalesianos.dam.Soccer.Football.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

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

    public Optional<Team> findById(Long id) {
        return repository.findById(id);

    }

    public Team save(NewTeamDto newTeamDto) {
        return repository.save(
                Team.builder()
                        .teamName(newTeamDto.getTeamName())
                        .build()
        );
    }

    public Team editDetails(Long id, EditTeamDto editTeamDto) {

        return repository.findById(id)
                .map(team -> {
                    team.setTeamName(editTeamDto.getTeamName());
                    return repository.save(team);
                })
                .orElseThrow(() ->new EntityNotFoundException("No team with id: " + id));
    }

    public Page<Team> search(List<SearchCriteria> params, Pageable pageable) {
        TeamSpecificationBuilder teamSpecificationBuilder =
                new TeamSpecificationBuilder(params);
        //GenericSpecificationBuilder<Person> personSpecificationBuilder =
        //        new GenericSpecificationBuilder<>(params, Person.class);
        Specification<Team> spec =  teamSpecificationBuilder.build();
        return repository.findAll(spec, pageable);
    }

    public void delete(Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
    }

}
