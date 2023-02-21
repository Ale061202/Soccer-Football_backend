package com.trianasalesianos.dam.Soccer.Football.league.model;

import com.trianasalesianos.dam.Soccer.Football.team.model.Team;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class League {

    @Id
    @GeneratedValue
    private Long id;

    private String league_name;

    @OneToMany
    private List<Team> teams = new ArrayList<>();

    public void addTeam(Team team) {
        teams.add(team);
    }

    public void deleteTeam(Team team) {
        teams.remove(team);
    }
}
