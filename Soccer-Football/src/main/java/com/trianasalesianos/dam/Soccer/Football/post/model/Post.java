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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    public void addCommentToPost(Comment c){
       comments.add(c);
       c.setPost(this);
    }

    public void deleteCommentToPost(Comment c){
        comments.remove(c);
        c.setPost(this);
    }


}
