package com.trianasalesianos.dam.Soccer.Football.team.model;
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
}
