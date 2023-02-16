package com.trianasalesianos.dam.Soccer.Football.exception;

import javax.persistence.EntityNotFoundException;

public class PostNotFoundException extends EntityNotFoundException {

    public PostNotFoundException() {
        super("The post could not be found");
    }

    public PostNotFoundException(Long id) {
        super(String.format("The post with id %d could not be found", id));
    }
}
