package com.trianasalesianos.dam.Soccer.Football.exception;

import javax.persistence.EntityNotFoundException;

public class LeagueNotFoundException extends EntityNotFoundException {

    public LeagueNotFoundException() {
        super("The league could not be found");
    }

    public LeagueNotFoundException(Long id) {
        super(String.format("The league with id %d could not be found", id));
    }
}
