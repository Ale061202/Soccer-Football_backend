package com.trianasalesianos.dam.Soccer.Football.exception;

import javax.persistence.EntityNotFoundException;

public class EmptyTeamException extends EntityNotFoundException {

    public EmptyTeamException() {
        super("No teams were found with the search criteria");
    }

}
