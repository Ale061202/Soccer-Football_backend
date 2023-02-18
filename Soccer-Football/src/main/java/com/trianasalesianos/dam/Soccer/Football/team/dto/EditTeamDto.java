package com.trianasalesianos.dam.Soccer.Football.team.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class EditTeamDto {
    @NotEmpty(message = "{teamDto.teamName.notempty}")
    private String teamName;
}
