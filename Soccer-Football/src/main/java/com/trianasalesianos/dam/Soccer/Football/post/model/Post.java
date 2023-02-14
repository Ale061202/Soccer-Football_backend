package com.trianasalesianos.dam.Soccer.Football.post.model;

import com.trianasalesianos.dam.Soccer.Football.comment.model.Comment;
import com.trianasalesianos.dam.Soccer.Football.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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

    @ManyToOne
    private User user;

    @OneToMany
    private List<Comment> commentList = new ArrayList<>();


}
