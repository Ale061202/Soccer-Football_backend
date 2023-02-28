package com.trianasalesianos.dam.Soccer.Football.exception;

import java.nio.file.AccessDeniedException;

public class NotPermission extends AccessDeniedException {
    public NotPermission(){
        super(String.format("Not have permission!"));
    }

}