package com.trianasalesianos.dam.Soccer.Football.post.dto;

import com.trianasalesianos.dam.Soccer.Football.post.model.Post;
import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class GetPostDto {

    private Long id;

    private String image;

    private String title;

    public static GetPostDto fromPost(Post post){
        return GetPostDto.builder()
                .id(post.getId())
                .image(post.getImage())
                .title(post.getTitle())
                .build();
    }
}
