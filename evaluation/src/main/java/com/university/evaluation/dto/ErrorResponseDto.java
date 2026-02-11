package com.university.evaluation.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDto {
    private String errorMsg;
    private String apiPath;
    private HttpStatus status;
    private LocalDateTime timeStamp;
}
