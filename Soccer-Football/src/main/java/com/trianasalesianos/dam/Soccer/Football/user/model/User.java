package com.trianasalesianos.dam.Soccer.Football.user.model;


import com.trianasalesianos.dam.Soccer.Football.esDel.EsDel;
import jakarta.persistence.OneToMany;
import lombok.*;

import com.trianasalesianos.dam.Soccer.Football.post.model.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
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


    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user")
    private List<EsDel> esDelList = new ArrayList<>();

    @OneToMany
    private List<Post> postList;
}
