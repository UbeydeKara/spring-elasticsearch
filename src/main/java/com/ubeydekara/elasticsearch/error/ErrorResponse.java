package com.ubeydekara.elasticsearch.error;

import lombok.Builder;

import java.util.Date;
import java.util.HashMap;

@Builder
public record ErrorResponse<T>(
        Integer status,
        Date timestamp,
        String message,
        String path,
        HashMap<String, T> errors
) {}