package com.beaconfire.project.trading.ordermanagement.exception.handler;

import com.beaconfire.project.trading.ordermanagement.exception.BusinessException;
import com.beaconfire.project.trading.ordermanagement.exception.ResourceNotFoundException;
import com.beaconfire.project.trading.ordermanagement.response.GeneralErrorResponse;
import com.beaconfire.project.trading.ordermanagement.response.ServiceStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class OrderManagementExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderManagementExceptionHandler.class);

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<GeneralErrorResponse> handleNullPointerException(NullPointerException e) {
        LOGGER.info("NullPointer exception: {}", e.getMessage());
        return ResponseEntity.badRequest().body(GeneralErrorResponse.builder()
                .serviceStatus(ServiceStatus.builder()
                        .success(false)
                        .statusCode("400")
                        .errorMessage(e.getMessage())
                        .build())
                .build());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<GeneralErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        LOGGER.info("Resource not found: {}", e.getMessage());
        return ResponseEntity.badRequest().body(GeneralErrorResponse.builder()
                .serviceStatus(ServiceStatus.builder()
                        .success(false)
                        .statusCode("404")
                        .errorMessage(e.getMessage())
                        .build())
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeneralErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        LOGGER.warn("Invalid request: {}", e.getMessage());
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(GeneralErrorResponse.builder()
                .serviceStatus(ServiceStatus.builder()
                        .success(false)
                        .statusCode("404")
                        .errorMessage(errors.toString())
                        .build())
                .build());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<GeneralErrorResponse> handleBusinessException(BusinessException e) {
        LOGGER.info("Business exception: {}", e.getMessage());
        return ResponseEntity.badRequest().body(GeneralErrorResponse.builder()
                .serviceStatus(ServiceStatus.builder()
                        .success(false)
                        .statusCode("500")
                        .errorMessage(e.getMessage())
                        .build())
                .build());
    }

    // handles method not supported exceptions
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<GeneralErrorResponse> handleMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                                         HttpServletRequest request) {
        String method = request.getMethod();
        String url = request.getRequestURL().toString();

        String message = String.format("HTTP method '%s' not supported for URL '%s'. Allowed methods are: %s",
                method, url, e.getSupportedHttpMethods());


        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(GeneralErrorResponse.builder()
                        .serviceStatus(ServiceStatus.builder()
                                .success(false)
                                .statusCode("405")
                                .errorMessage(message)
                                .build())
                        .build());
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<GeneralErrorResponse> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException e, HttpServletRequest request) {

        StringBuilder builder = new StringBuilder();
        builder.append(e.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        e.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));

        if (builder.length() > 0) {
            builder.setLength(builder.length() - 2);
        }

        return ResponseEntity
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(GeneralErrorResponse.builder()
                        .serviceStatus(ServiceStatus.builder()
                                .success(false)
                                .statusCode("415")
                                .errorMessage(builder.toString())
                                .build())
                        .build());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<GeneralErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, WebRequest request) {
        String error = "Invalid JSON format or incorrect data. Please check your request body.";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                GeneralErrorResponse.builder()
                .serviceStatus(ServiceStatus.builder()
                        .success(false)
                        .statusCode("400")
                        .errorMessage(error)
                        .build())
                .build());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<GeneralErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        String error = "The requested URL " + ex.getRequestURL() + " was not found on this server.";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GeneralErrorResponse.builder()
                .serviceStatus(ServiceStatus.builder()
                        .success(false)
                        .statusCode("404")
                        .errorMessage(error)
                        .build())
                .build());
    }
}