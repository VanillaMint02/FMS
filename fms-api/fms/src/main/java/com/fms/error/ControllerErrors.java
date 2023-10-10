package com.fms.error;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.*;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.fms.error.message.ErrorMessages.ENTITY_COULD_NOT_HAVE_BEEN_FOUND;

@ControllerAdvice
public class ControllerErrors extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            RuntimeException ex, WebRequest request) {

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("error", "Entity Not Found");
        responseBody.put("message", ex.getMessage());
        responseBody.put("timestamp", new Date());
        responseBody.put("status","400");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        return handleExceptionInternal(ex, responseBody,
                headers, HttpStatus.BAD_REQUEST, request);
    }
    @ExceptionHandler(value= EntityExistsException.class)
    protected ResponseEntity<Object> handleEntityExists(
            RuntimeException ex, WebRequest request) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("error", "Entity Already Exists");
        responseBody.put("message", ex.getMessage());
        responseBody.put("timestamp", new Date());
        responseBody.put("status","400");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(ex, responseBody,
                headers, HttpStatus.BAD_REQUEST, request);
    }
}
