package com.trianasalesianos.dam.Soccer.Football.team.model;
import com.trianasalesianos.dam.Soccer.Football.league.model.League;
import com.trianasalesianos.dam.Soccer.Football.user.model.User;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private League league;

    @ManyToOne
    private User user;
}
