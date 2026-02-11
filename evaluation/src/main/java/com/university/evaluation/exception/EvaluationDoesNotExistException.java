package com.university.evaluation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EvaluationDoesNotExistException extends  RuntimeException {
    public EvaluationDoesNotExistException(String msg) {
        super(msg);
    }
}
