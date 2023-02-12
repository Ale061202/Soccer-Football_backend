package com.trianasalesianos.dam.Soccer.Football.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class Post {

    @Id @GeneratedValue
    private Long id;

    private String content;
    private String postPhoto;
    private Integer likeCount;
    private Integer commentCount;
    private Integer shareCount;
}
