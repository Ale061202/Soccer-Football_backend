package com.trianasalesianos.dam.Soccer.Football.league.repository;

import com.trianasalesianos.dam.Soccer.Football.league.model.League;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueRepository extends JpaRepository<League, Long> {
}
