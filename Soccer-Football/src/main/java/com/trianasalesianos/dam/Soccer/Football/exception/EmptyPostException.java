package com.trianasalesianos.dam.Soccer.Football.exception;

import javax.persistence.EntityNotFoundException;

public class EmptyPostException extends EntityNotFoundException {

    public EmptyPostException() {
        super("No posts were found with the search criteria");
    }

}
