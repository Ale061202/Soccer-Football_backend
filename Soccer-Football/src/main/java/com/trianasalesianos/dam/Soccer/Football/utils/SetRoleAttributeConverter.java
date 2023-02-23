package com.trianasalesianos.dam.Soccer.Football.utils;

import com.trianasalesianos.dam.Soccer.Football.user.model.UserRole;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class SetRoleAttributeConverter implements AttributeConverter<Set<UserRole>,String> {

    private final String SEPARATOR = ",";
    @Override
    public String convertToDatabaseColumn(Set<UserRole> attribute) {
        if(!attribute.isEmpty()){
            return attribute.stream()
                    .map(UserRole::name)
                    .collect(Collectors.joining(SEPARATOR));
        }
        return null;
    }

    @Override
    public Set<UserRole> convertToEntityAttribute(String dbData) {
        if (dbData != null && !dbData.isBlank()){
            return Arrays.stream(dbData.split(SEPARATOR))
                    .map(UserRole::valueOf)
                    .collect(Collectors.toCollection(()-> new HashSet<UserRole>()));
        }
        return new HashSet<>();
    }
}
