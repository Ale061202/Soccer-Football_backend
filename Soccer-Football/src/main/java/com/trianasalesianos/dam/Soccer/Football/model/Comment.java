package com.trianasalesianos.dam.Soccer.Football.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Comment {
    @Id @GeneratedValue
    private Long id;

    private String content;

    private int likeCounter;

    private Date dateCreated;

    private User author;

    private Post post;


}
