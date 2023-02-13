package com.trianasalesianos.dam.Soccer.Football.user.model;

import com.trianasalesianos.dam.Soccer.Football.post.model.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class User {

    @Id @GeneratedValue
    private UUID id;

    private String username;

    private String password;

    private String email;

    private String first_name;

    private String last_name;

    private String avatar;

    private Date birthDate;

    @ManyToMany
    private Post post;
}
