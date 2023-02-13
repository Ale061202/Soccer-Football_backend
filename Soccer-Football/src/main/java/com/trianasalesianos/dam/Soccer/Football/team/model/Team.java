package com.trianasalesianos.dam.Soccer.Football.team.model;

import com.trianasalesianos.dam.Soccer.Football.esDel.EsDel;
import com.trianasalesianos.dam.Soccer.Football.league.model.League;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "team")
    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<EsDel> esDelList = new ArrayList<>();
}
