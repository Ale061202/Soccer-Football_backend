package com.trianasalesianos.dam.Soccer.Football.exception;

import javax.persistence.EntityNotFoundException;

public class EmptyUserException extends EntityNotFoundException {

    public EmptyUserException() {
        super("No users were found with the search criteria");
    }
}
