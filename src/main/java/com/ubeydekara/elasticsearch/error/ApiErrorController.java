package com.ubeydekara.elasticsearch.error;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiErrorController implements ErrorController {
    private final ErrorAttributes errorAttributes;

    /** Handling api errors */
    @RequestMapping("/error")
    public ErrorResponse handleError(WebRequest request) {
        Map<String, Object> attributes = errorAttributes.getErrorAttributes(
            request,
            ErrorAttributeOptions.of(
                    ErrorAttributeOptions.Include.BINDING_ERRORS,
                    ErrorAttributeOptions.Include.MESSAGE
            )
        );

        int status = (int) attributes.get("status");
        String message = (String) attributes.get("message");
        String path = (String) attributes.get("path");
        Map<String, Object> validationErrors = new HashMap<>();

        log.error(message);

        if (attributes.containsKey("errors")) {
            List<FieldError> fieldErrorList = (List<FieldError>) attributes.get("errors");

            for (FieldError fieldError : fieldErrorList){
                validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
        }
        else {
            validationErrors = null;
        }

        return new ErrorResponse(
                status,
                new Date(),
                message,
                path,
                validationErrors
        );
    }
}
