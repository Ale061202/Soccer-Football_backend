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
@Table(name="league_entity")
public class League {

    @Id
    @GeneratedValue
    private Long id;

    private String league_name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "league")
    private List<Team> teams = new ArrayList<>();

    public void addTeam(Team team) {
        teams.add(team);
    }

    public void deleteTeam(Team team) {
        teams.remove(team);
    }

    /*public String getTeamNames(List<Team> team){
        String result = null;
        for (Team name : team){
            result = name.getName();
        }
        return result;
    }*/
}
