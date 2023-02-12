package com.trianasalesianos.dam.Soccer.Football.repository;

import com.trianasalesianos.dam.Soccer.Football.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
