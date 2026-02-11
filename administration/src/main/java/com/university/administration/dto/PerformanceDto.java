package com.university.administration.dto;


import com.university.administration.enums.Grade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceDto {

    private String enrollmentNo;

    private String papperId;


    private Grade grade;


    private int attemptNo;
}
