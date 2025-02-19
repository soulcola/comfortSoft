package com.petrtitov.comfortSoft.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.petrtitov.comfortSoft.config.ErrorType.APP_ERROR;
import static com.petrtitov.comfortSoft.config.ErrorType.INPUT_ERROR;

@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE + 5)
@Slf4j
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorInfo wrongDataError(Exception e, HttpServletRequest request) {
        return logAndGetErrorInfo(e, INPUT_ERROR, request);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IOException.class)
    public ErrorInfo fileReadError(Exception e, HttpServletRequest request) {
        return logAndGetErrorInfo(e, INPUT_ERROR, request);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorInfo internalError(Exception e, HttpServletRequest request) {
        return logAndGetErrorInfo(e, APP_ERROR, request);
    }

    private static ErrorInfo logAndGetErrorInfo(Exception e, ErrorType errorType, HttpServletRequest request) {
        log.error("{}: Exception {} at request {}",errorType, e, request.getRequestURI());
        List<String> errors = new ArrayList<>();
        errors.add(e.getMessage());
        return new ErrorInfo(errorType, errors);
    }
}
