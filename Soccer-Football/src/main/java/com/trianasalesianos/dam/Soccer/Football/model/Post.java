package com.trianasalesianos.dam.Soccer.Football.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Post {

    @Id @GeneratedValue
    private Long id;

    private String postPhoto;

    private int likesCounter;

    private User author;

    private Date dateCreated;

    private List<Comment> postComments = new ArrayList<>();

}
