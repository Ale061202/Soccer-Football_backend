package com.trianasalesianos.dam.Soccer.Football.comment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.trianasalesianos.dam.Soccer.Football.comment.model.Comment;
import com.trianasalesianos.dam.Soccer.Football.user.model.User;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class GetCommentDto {

    private Long id;

    private String content;

    private String author;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    protected LocalDate upload;


    public static GetCommentDto fromComment(Comment comment){
        return GetCommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .author(comment.getUser().getUsername())
                .upload(comment.getDate())
                .build();
    }
}
