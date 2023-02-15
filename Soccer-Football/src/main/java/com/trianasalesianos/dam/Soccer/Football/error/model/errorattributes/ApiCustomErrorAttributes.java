package com.trianasalesianos.dam.Soccer.Football.error.model.errorattributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trianasalesianos.dam.Soccer.Football.error.model.ApiError;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
@Component
@RequiredArgsConstructor
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class ApiCustomErrorAttributes extends DefaultErrorAttributes {

    private final ObjectMapper objectMapper;

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String,Object> defaultErrorAttributes = super.getErrorAttributes(webRequest, options);
        ApiError apiError = ApiError.fromErrorAttributes(defaultErrorAttributes);
        return objectMapper.convertValue(apiError, Map.class);
    }
}
