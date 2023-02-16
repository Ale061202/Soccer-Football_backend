package com.trianasalesianos.dam.Soccer.Football.exception;

import javax.persistence.EntityNotFoundException;

public class TeamNotFoundException extends EntityNotFoundException {

    public TeamNotFoundException() {
        super("The team could not be found");
    }

    public TeamNotFoundException(Long id) {
        super(String.format("The team with id %d could not be found", id));
    }
}
