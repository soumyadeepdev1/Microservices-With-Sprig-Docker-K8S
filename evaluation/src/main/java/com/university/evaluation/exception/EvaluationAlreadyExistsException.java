package com.university.evaluation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EvaluationAlreadyExistsException extends RuntimeException{
    public  EvaluationAlreadyExistsException(String message) {
        super(message);
    }
}
