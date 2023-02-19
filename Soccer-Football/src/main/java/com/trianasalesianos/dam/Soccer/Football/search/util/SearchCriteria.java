package com.trianasalesianos.dam.Soccer.Football.search.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class SearchCriteria {

    private String key;
    private String operator;
    private Object value;

}
