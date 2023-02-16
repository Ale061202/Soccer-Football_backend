package com.trianasalesianos.dam.Soccer.Football.exception;

import javax.persistence.EntityNotFoundException;

public class EmptyLeagueException extends EntityNotFoundException {

    public EmptyLeagueException() {
        super("No leagues were found with the search criteria");
    }

}
