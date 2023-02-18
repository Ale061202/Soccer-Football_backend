package com.trianasalesianos.dam.Soccer.Football.team.dto;

import com.trianasalesianos.dam.Soccer.Football.validation.annotation.UniqueUsername;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class NewTeamDto {
    @UniqueUsername(message = "{teamDto.teamName.unique}")
    @NotEmpty(message = "{teamDto.teamName.notempty}")
    private String teamName;


}
