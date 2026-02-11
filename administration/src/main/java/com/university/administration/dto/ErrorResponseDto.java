package com.university.administration.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {
    private String errorMsg;
    private HttpStatus errorCode;
    private String apiPath;
    private LocalDateTime timestamp;

}
