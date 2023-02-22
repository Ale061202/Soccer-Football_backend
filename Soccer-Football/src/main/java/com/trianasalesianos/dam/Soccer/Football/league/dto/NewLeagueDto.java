package com.trianasalesianos.dam.Soccer.Football.league.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class NewLeagueDto {
    @NotEmpty(message = "{leagueDto.leagueName.notempty}")
    private String league_name;
}
