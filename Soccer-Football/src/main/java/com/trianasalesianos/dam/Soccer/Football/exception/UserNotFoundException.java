package com.trianasalesianos.dam.Soccer.Football.exception;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException() {
        super("The user could not be found");
    }

    public UserNotFoundException(UUID id) {
        super(String.format("The comment with uuid %d could not be found", id));
    }
}
