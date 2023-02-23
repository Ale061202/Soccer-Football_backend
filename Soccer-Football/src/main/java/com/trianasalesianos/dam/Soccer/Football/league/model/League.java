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
@NamedEntityGraph
        (name = "league-with-teams",
            attributeNodes = {
        @NamedAttributeNode(value = "teams")
            }
        )
public class League {

    @Id
    @GeneratedValue
    private Long id;

    private String league_name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "league")
    @Builder.Default
    private List<Team> teams = new ArrayList<>();

    public void addTeam(Team team) {
        teams.add(team);
    }

    public void deleteTeam(Team team) {
        teams.remove(team);
    }

}
