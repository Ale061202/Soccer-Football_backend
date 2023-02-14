package com.trianasalesianos.dam.Soccer.Football.validation.validator;

import com.trianasalesianos.dam.Soccer.Football.user.service.UserService;
import com.trianasalesianos.dam.Soccer.Football.validation.annotation.UniqueUsername;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.hasText(s) && !userService.userExists(s);
    }
}
