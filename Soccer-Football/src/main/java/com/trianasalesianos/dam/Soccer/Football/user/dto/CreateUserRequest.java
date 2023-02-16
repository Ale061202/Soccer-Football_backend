package com.trianasalesianos.dam.Soccer.Football.user.dto;

import lombok.*;

import javax.persistence.Entity;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class CreateUserRequest {

    private String username;

    private String password;

    private String verifyPassword;

    private String avatar;

    private String first_name;

    private String last_name;
}
