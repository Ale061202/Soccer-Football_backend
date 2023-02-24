package com.trianasalesianos.dam.Soccer.Football.post.dto;

import com.trianasalesianos.dam.Soccer.Football.comment.dto.GetCommentDto;
import com.trianasalesianos.dam.Soccer.Football.post.model.Post;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class GetPostDto {

    private Long id;

    private String image;

    private String title;

    private List<GetCommentDto> comments = new ArrayList<>();


    public static GetPostDto fromPost(Post post){
        return GetPostDto.builder()
                .id(post.getId())
                .image(post.getImage())
                .title(post.getTitle())
                .comments(post.getComments().stream()
                        .map(c -> GetCommentDto.builder()
                                .id(c.getId())
                                .author(c.getAuthor())
                                .content(c.getContent())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
