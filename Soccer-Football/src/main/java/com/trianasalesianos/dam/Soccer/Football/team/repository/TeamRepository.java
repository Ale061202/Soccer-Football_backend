package com.trianasalesianos.dam.Soccer.Football.team.repository;

import com.trianasalesianos.dam.Soccer.Football.team.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Long> {
}
