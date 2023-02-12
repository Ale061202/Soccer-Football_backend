package com.trianasalesianos.dam.Soccer.Football.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class Comment {

    @Id @GeneratedValue
    private Long id;

    private String content;
    private Integer likeCount;

    private Date dateCreated;

    private User author;

    private Post post;

}
