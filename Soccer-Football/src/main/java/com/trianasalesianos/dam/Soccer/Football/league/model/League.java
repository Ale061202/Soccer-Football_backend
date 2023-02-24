package com.trianasalesianos.dam.Soccer.Football.league.model;

import com.trianasalesianos.dam.Soccer.Football.team.model.Team;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class League {

    @Id
    @GeneratedValue
    private Long id;

    private String league_name;

    @OneToMany(mappedBy = "league",fetch = FetchType.LAZY)
    @Builder.Default
    private List<Team> teams = new ArrayList<>();

    //Helper Team

    public void addTeamToLeague(Team t){
        teams.add(t);
        t.setLeague(this);
    }

    public void removeTeamToLeague(Team t){
        teams.remove(t);
        t.setLeague(null);
    }

}
