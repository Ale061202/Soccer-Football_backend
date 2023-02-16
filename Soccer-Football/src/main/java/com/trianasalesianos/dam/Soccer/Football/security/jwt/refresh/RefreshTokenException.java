package com.trianasalesianos.dam.Soccer.Football.security.jwt.refresh;

import com.trianasalesianos.dam.Soccer.Football.security.errorhandling.JwtTokenException;

public class RefreshTokenException extends JwtTokenException {

    public RefreshTokenException(String msg) {
        super(msg);
    }
}
