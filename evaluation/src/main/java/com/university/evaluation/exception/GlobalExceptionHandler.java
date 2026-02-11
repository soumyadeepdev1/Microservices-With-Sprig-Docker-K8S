package com.university.evaluation.exception;

import com.university.evaluation.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMsg);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorResponseDto(
                        "Failed parsing request body",
                        request.getDescription(false),
                        HttpStatus.BAD_REQUEST,
                        LocalDateTime.now()
                )
        ,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = EvaluationDoesNotExistException.class)
    public ResponseEntity<Object> studentAlreadyExistsResponseHandler(EvaluationDoesNotExistException ex, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorResponseDto(
                        ex.getMessage(),

                        request.getDescription(false),
                        HttpStatus.NOT_FOUND,
                        LocalDateTime.now()
                ), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = EvaluationAlreadyExistsException.class)
    public ResponseEntity<Object> studentDoesNotExistsResponseHandler(EvaluationAlreadyExistsException ex, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorResponseDto(
                        ex.getMessage(),

                        request.getDescription(false),
                        HttpStatus.BAD_REQUEST,
                        LocalDateTime.now()
                ), HttpStatus.BAD_REQUEST);
    }
}
