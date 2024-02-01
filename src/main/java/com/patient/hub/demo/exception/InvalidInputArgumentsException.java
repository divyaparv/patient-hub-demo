package com.patient.hub.demo.exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

public class InvalidInputArgumentsException extends RuntimeException {
    private final BindingResult errors;

    public InvalidInputArgumentsException(BindingResult errors) {
        this.errors = errors;
    }

    @Override
    public String getMessage() {
        return this.getAllMessages().toString();
    }

    private List<String> getAllMessages() {
        return this.getAllErrorMessages(this.errors);
    }

    private List<String> getAllErrorMessages(BindingResult bindingResult) {
        return bindingResult.getAllErrors()
                .stream()
                .map(InvalidInputArgumentsException::getErrorMessage)
                .collect(Collectors.toList());
    }

    private static String getErrorMessage(ObjectError objectError) {
        if (objectError instanceof FieldError fieldError) {
            String className = fieldError.getObjectName();
            String property = fieldError.getField();
            Object invalidValue = fieldError.getRejectedValue();
            String message = fieldError.getDefaultMessage();
            return String.format("%s.%s %s, but it was %s", className, property, message, invalidValue);
        }
        return String.format("%s: %s", objectError.getObjectName(), objectError.getDefaultMessage());
    }

}
