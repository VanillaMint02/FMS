package com.fms.error;

import com.fms.error.custom.errors.InvalidDataException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerErrors extends ResponseEntityExceptionHandler {

    private static HttpHeaders getHttpHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private static Map<String, Object> getResponseBody(RuntimeException ex) {

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("error", ex.getClass().getSimpleName());
        responseBody.put("message", ex.getMessage());
        responseBody.put("timestamp", new Date());
        responseBody.put("status", 400);
        return responseBody;
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            RuntimeException ex, WebRequest request) {

        return handleExceptionInternal(ex, getResponseBody(ex),
                getHttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = EntityExistsException.class)
    protected ResponseEntity<Object> handleEntityExists(
            RuntimeException ex, WebRequest request) {

        return handleExceptionInternal(ex, getResponseBody(ex),
                getHttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = InvalidDataException.class)
    protected ResponseEntity<Object> handleInvalidAttributeValue(
            RuntimeException ex, WebRequest request) {

        return handleExceptionInternal(ex,getResponseBody(ex),getHttpHeaders(),HttpStatus.BAD_REQUEST,request);
    }
}
