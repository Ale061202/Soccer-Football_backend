package com.trianasalesianos.dam.Soccer.Football.exception;

import javax.persistence.EntityNotFoundException;

public class CommentNotFoundException extends EntityNotFoundException {

    public CommentNotFoundException() {
        super("The comment could not be found");
    }

    public CommentNotFoundException(Long id) {
        super(String.format("The comment with id %d could not be found", id));
    }
}
