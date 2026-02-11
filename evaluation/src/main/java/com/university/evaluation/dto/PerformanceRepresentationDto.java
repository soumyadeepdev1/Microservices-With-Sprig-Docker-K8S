package com.university.evaluation.dto;


import com.university.evaluation.enums.Grade;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceRepresentationDto {

    @NotEmpty(message = "Student Enrollment no can not be null")
    @Pattern(regexp = "^MAKAUT_[0-9]{4,}$",message ="please provide a valid pattren")
    private String enrollmentNo;
    @NotEmpty(message = "PaperId can not be null")
    private String papperId;

    @NotNull(message = "Grade can not be null")
    private Grade grade;

    @Min(value = 1, message = "Attempt must be greater than 0")
    private int attemptNo;
}
