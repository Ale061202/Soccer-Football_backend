package com.trianasalesianos.dam.Soccer.Football.model;

import com.trianasalesianos.dam.Soccer.Football.team.model.Team;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class League {
    @Id @GeneratedValue
    private Long id;

    private String league;
    private List<Team> teams;
}
