package com.trianasalesianos.dam.Soccer.Football.comment.model;

import com.trianasalesianos.dam.Soccer.Football.post.model.Post;
import com.trianasalesianos.dam.Soccer.Football.user.model.User;
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