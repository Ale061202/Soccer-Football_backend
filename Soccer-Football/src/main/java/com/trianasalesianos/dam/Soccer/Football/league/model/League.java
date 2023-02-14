package com.trianasalesianos.dam.Soccer.Football.league.model;

import com.trianasalesianos.dam.Soccer.Football.team.model.Team;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class League {

    @Id
    @GeneratedValue
    private Long id;

    private String league_name;

    @OneToMany
    private List<Team> teamList = new ArrayList<>();
}
