package com.ignat.chernyshov.profile.exception;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ignat.chernyshov.profile.exception.exceptions.ProfileNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(ProfileNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleProfileNotFoundException(ProfileNotFoundException exception, Locale locale) {
        log.info("ProfileNotFoundException: {}", exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, 
                this.messageSource.getMessage(exception.getMessage(), new Object[0], 
                    exception.getMessage(), locale)));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        log.warn("MethodArgumentNotValidException: {}", exception.getMessage());

        Locale locale = LocaleContextHolder.getLocale();

        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> {
                    String message = messageSource.getMessage(fieldError, locale);
                    return String.format("%s: %s", fieldError.getField(), message);
                })
                .collect(Collectors.toList());

        String detailMessage = String.join("; ", errors);

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, 
                detailMessage
        );

        return this.handleExceptionInternal(exception, problemDetail, headers, status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleException(Exception exception, Locale locale) {
        log.error("Unexpected error occurred: ", exception);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,
                this.messageSource.getMessage("Ошибка на сервере", new Object[0],
                    "Ошибка на сервере", locale)));
    }
}
