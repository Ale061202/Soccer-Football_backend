package com.trianasalesianos.dam.Soccer.Football.league.dto;

import com.trianasalesianos.dam.Soccer.Football.league.model.League;
import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class GetLeagueDto {

    private Long id;

    private String league_name;

    public static GetLeagueDto fromLeague(League league){
        return GetLeagueDto.builder()
                .id(league.getId())
                .league_name(league.getLeague_name())
                .build();
    }
}
