package com.university.administration.dto;

import com.university.administration.enums.AvailableCourse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentAllInfoDto {

    private String studentName;


    private String mobileNumber;


    private AvailableCourse enrolledCourse;


    private String email;


    private String address;


    private String countryCode;

    private List<PerformanceDto> performances;
}
