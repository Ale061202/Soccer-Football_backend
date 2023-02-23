package com.trianasalesianos.dam.Soccer.Football.comment.dto;

import com.trianasalesianos.dam.Soccer.Football.comment.model.Comment;
import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class GetCommentDto {

    private Long id;

    private String content;

    private String author;

    public static GetCommentDto fromComment(Comment comment){
        return GetCommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .author(comment.getAuthor())
                .build();
    }
}
