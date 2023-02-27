package com.trianasalesianos.dam.Soccer.Football.comment.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewCommentDto {
    @NotEmpty(message = "{commentDto.content.notempty}")
    private String content;
}
