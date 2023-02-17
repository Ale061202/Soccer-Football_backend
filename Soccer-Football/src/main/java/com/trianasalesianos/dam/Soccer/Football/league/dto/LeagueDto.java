package com.trianasalesianos.dam.Soccer.Football.league.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeagueDto {
    @NotEmpty(message = "{leagueDto.leagueName.notempty}")
    private String league_name;
}
