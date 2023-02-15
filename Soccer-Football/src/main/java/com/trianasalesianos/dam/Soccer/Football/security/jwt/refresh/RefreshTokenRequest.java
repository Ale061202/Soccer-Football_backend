package com.trianasalesianos.dam.Soccer.Football.security.jwt.refresh;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class RefreshTokenRequest {

    private String refreshToken;
}
