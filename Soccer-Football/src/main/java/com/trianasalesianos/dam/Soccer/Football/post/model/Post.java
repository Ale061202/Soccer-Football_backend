package com.trianasalesianos.dam.Soccer.Football.post.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class Post {

    @Id @GeneratedValue
    private Long id;

    private String content;

    private String postPhoto;

    private int likeCount;

    private int commentCount;
}
