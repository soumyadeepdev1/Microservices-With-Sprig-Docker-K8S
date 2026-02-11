package com.university.administration.dto;

import com.university.administration.enums.AvailableCourse;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentRepresentationDto {


    @NotEmpty(message = "student name cannot be blank")
    private String studentName;

    @NotEmpty(message = "Mobile number cannot be null")

    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid mobile number. Must be 10 digits starting with 6-9")
    private String mobileNumber;

    @NotNull(message = "Enrolled course cannot be null")
    private AvailableCourse enrolledCourse;

    @NotEmpty(message = "Email cannot be blank")

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Address cannot be blank")
    private String address;

    @NotEmpty(message = "Country code cannot be blank")
    @Pattern(regexp = "^[+]\\d{2}$", message = "Invalid email format")
    private String countryCode;

}
