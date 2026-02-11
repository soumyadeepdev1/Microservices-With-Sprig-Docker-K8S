package com.university.administration.entity;


import com.university.administration.enums.AvailableCourse;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "makaut_students")
public class StudentEntity {
    @Id
    private String enrollmentNo;


    private String studentName;

    private String mobileNumber;

    private AvailableCourse enrolledCourse;

    private String email;

    private String address;

    private String countryCode;

}
