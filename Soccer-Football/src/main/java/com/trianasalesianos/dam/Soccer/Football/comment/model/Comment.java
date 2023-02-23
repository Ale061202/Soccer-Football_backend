package com.trianasalesianos.dam.Soccer.Football.comment.model;

import com.trianasalesianos.dam.Soccer.Football.post.model.Post;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    private LocalDateTime uploadDate = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;
}
