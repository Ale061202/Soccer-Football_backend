package com.trianasalesianos.dam.Soccer.Football.team.model;

import com.trianasalesianos.dam.Soccer.Football.league.model.League;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class Team {

    @Id @GeneratedValue
    private Long id;

    private String name;
    @OneToMany
    private League league;
}
