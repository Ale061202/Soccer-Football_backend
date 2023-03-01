package com.trianasalesianos.dam.Soccer.Football.user.dto;

import com.trianasalesianos.dam.Soccer.Football.team.model.Team;
import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class CreateUserRequest {

    private String username;

    private String password;

    private String avatar;

    private String first_name;

    private String last_name;

    private String phone;

    private String email;

}
