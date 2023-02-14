package com.trianasalesianos.dam.Soccer.Football.team.model;
import com.trianasalesianos.dam.Soccer.Football.league.model.League;
import com.trianasalesianos.dam.Soccer.Football.user.model.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class Team {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private League league;

    @ManyToOne
    private User user;
}
