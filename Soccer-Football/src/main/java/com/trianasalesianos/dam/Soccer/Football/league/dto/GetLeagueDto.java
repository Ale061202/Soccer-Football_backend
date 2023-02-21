package com.trianasalesianos.dam.Soccer.Football.league.dto;

import com.trianasalesianos.dam.Soccer.Football.league.model.League;
import com.trianasalesianos.dam.Soccer.Football.team.model.Team;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class GetLeagueDto {

    private Long id;

    private String league_name;

    private List<Team> teams = new ArrayList<>();


    public static GetLeagueDto fromLeague(League league){
        return GetLeagueDto.builder()
                .id(league.getId())
                .league_name(league.getLeague_name())
                .teams(league.getTeams())
                .build();
    }
}
