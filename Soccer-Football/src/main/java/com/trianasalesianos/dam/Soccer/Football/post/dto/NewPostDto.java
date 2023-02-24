package com.trianasalesianos.dam.Soccer.Football.post.dto;

import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewPostDto {
    @NotEmpty(message = "{postDto.title.notempty}")
    private String title;

}
