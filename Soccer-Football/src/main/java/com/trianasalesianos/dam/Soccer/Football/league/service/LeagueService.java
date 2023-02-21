package com.trianasalesianos.dam.Soccer.Football.league.service;

import com.trianasalesianos.dam.Soccer.Football.league.dto.EditLeagueDto;
import com.trianasalesianos.dam.Soccer.Football.league.model.League;
import com.trianasalesianos.dam.Soccer.Football.league.repository.LeagueRepository;
import com.trianasalesianos.dam.Soccer.Football.search.spec.LeagueSpecificationBuilder;
import com.trianasalesianos.dam.Soccer.Football.search.util.SearchCriteria;
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
public class LeagueService {
    private final LeagueRepository repository;


    public List<League> findAll() {

        List<League> result = repository.findAll();

        if (result.isEmpty())
            throw new EntityNotFoundException("No users with this search criteria");

        return repository.findAll();
    }

    public Optional<League> findById(Long id) {
        return repository.findById(id);

    }

    public League save(League league) {
        return repository.save(league);
    }

    public League editDetails(Long id, EditLeagueDto editLeagueDto) {

        return repository.findById(id)
                .map(league -> {
                    league.setLeague_name(editLeagueDto.getLeague_name());
                    return repository.save(league);
                })
                .orElseThrow(() ->new EntityNotFoundException("No user with id: " + id));


    }

    public Page<League> search(List<SearchCriteria> params, Pageable pageable) {
        LeagueSpecificationBuilder leagueSpecificationBuilder =
                new LeagueSpecificationBuilder(params);
        //GenericSpecificationBuilder<Person> personSpecificationBuilder =
        //        new GenericSpecificationBuilder<>(params, Person.class);
        Specification<League> spec =  leagueSpecificationBuilder.build();
        return repository.findAll(spec, pageable);
    }

    public void delete(Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
    }

}
