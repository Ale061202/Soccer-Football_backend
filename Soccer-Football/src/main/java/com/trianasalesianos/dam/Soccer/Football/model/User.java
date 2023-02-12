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
public class User {
    @Id @GeneratedValue
    private Long id;

    private String username;

    private String password;

    private String email;

    private String first_name;

    private String last_name;

    private String avatar;

    private Date birthDate;
}
