package com.trianasalesianos.dam.Soccer.Football.post.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {
    @NotEmpty(message = "{postDto.title.notempty}")
    private String title;
}
