package com.trianasalesianos.dam.Soccer.Football.post.dto;

import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class EditPostDto {
    @NotEmpty(message = "{postDto.title.notempty}")
    private String title;
    @URL(message = "{postDto.image.url}")
    private String image;
}
