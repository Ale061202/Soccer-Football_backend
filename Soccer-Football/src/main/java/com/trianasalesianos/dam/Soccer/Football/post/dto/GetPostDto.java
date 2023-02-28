package com.trianasalesianos.dam.Soccer.Football.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.trianasalesianos.dam.Soccer.Football.comment.dto.GetCommentDto;
import com.trianasalesianos.dam.Soccer.Football.post.model.Post;
import com.trianasalesianos.dam.Soccer.Football.user.model.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
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

    private String author;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    protected LocalDate upload;

    private List<GetCommentDto> comments = new ArrayList<>();


    public static GetPostDto fromPost(Post post){
        return GetPostDto.builder()
                .id(post.getId())
                .image(post.getImage())
                .title(post.getTitle())
                .author(post.getUser().getUsername())
                .upload(post.getDate())
                .comments(post.getComments().stream()
                        .map(GetCommentDto::fromComment).collect(Collectors.toList()))
                .build();
    }
}
