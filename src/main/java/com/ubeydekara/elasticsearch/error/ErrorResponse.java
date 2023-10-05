package com.ubeydekara.elasticsearch.error;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse<T>(
        Integer status,
        Date timestamp,
        String message,
        String path,
        Map<String, T> errors
) {}
