package com.trianasalesianos.dam.Soccer.Football.post.model;

import com.trianasalesianos.dam.Soccer.Football.comment.model.Comment;
import com.trianasalesianos.dam.Soccer.Football.user.model.User;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    private String image;

    private String title;

    @ManyToOne
    private User user;

    @OneToMany
    private List<Comment> commentList = new ArrayList<>();


}
