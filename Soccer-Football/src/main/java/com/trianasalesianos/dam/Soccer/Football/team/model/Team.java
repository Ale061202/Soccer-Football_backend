package com.trianasalesianos.dam.Soccer.Football.team.model;
import com.trianasalesianos.dam.Soccer.Football.league.model.League;
import com.trianasalesianos.dam.Soccer.Football.user.model.User;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    private String teamName;

    @ManyToOne(fetch = FetchType.EAGER)
    private League league;

    private LocalDateTime uploadDate = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
}
