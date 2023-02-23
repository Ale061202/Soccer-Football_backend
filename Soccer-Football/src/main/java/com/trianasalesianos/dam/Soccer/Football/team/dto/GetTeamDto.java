package com.trianasalesianos.dam.Soccer.Football.team.dto;

import com.trianasalesianos.dam.Soccer.Football.team.model.Team;
import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class GetTeamDto {

    private Long id;

    private String teamName;


    public static GetTeamDto fromTeam(Team team){
        return GetTeamDto.builder()
                .id(team.getId())
                .teamName(team.getTeamName())
                .build();
    }
}
