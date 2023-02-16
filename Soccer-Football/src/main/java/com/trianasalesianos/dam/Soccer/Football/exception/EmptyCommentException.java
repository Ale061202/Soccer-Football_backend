package com.trianasalesianos.dam.Soccer.Football.exception;

import javax.persistence.EntityNotFoundException;

public class EmptyCommentException extends EntityNotFoundException {

    public EmptyCommentException() {
        super("No comments were found with the search criteria");
    }

}
