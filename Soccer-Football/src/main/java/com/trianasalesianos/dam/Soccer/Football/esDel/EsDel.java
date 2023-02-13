package com.trianasalesianos.dam.Soccer.Football.esDel;

import com.trianasalesianos.dam.Soccer.Football.team.model.Team;
import com.trianasalesianos.dam.Soccer.Football.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class EsDel {

    @EmbeddedId
    private EsDelPk id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("team_id")
    @JoinColumn(name = "team_id")
    private Team team;
}
