package com.trianasalesianos.dam.Soccer.Football.team.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class TeamDto {
    @NotEmpty(message = "{teamDto.name.notempty}")
    private String name;
}
