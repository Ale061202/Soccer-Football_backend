package com.trianasalesianos.dam.Soccer.Football.team.dto;

import com.trianasalesianos.dam.Soccer.Football.league.model.League;
import com.trianasalesianos.dam.Soccer.Football.team.model.Team;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class GetTeamDto {

    private Long id;

    private String teamName;

    private String leagueName;


    public static GetTeamDto fromTeam(Team team){
        return GetTeamDto.builder()
                .id(team.getId())
                .teamName(team.getTeamName())
                .leagueName(team.getLeague().getLeague_name())
                .build();
    }
}
