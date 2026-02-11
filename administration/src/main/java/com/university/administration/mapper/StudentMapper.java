package com.university.administration.mapper;

import com.university.administration.dto.StudentRepresentationDto;
import com.university.administration.entity.StudentEntity;

public class StudentMapper {
    public static StudentEntity DtoToEntity(StudentRepresentationDto studentRepresentationDto,StudentEntity studentEntity){



        studentEntity.setStudentName(studentRepresentationDto.getStudentName());
        studentEntity.setEmail(studentRepresentationDto.getEmail());
        studentEntity.setEnrolledCourse(studentRepresentationDto.getEnrolledCourse());
        studentEntity.setMobileNumber(studentRepresentationDto.getMobileNumber());
        studentEntity.setCountryCode(studentRepresentationDto.getCountryCode());
        studentEntity.setAddress(studentRepresentationDto.getAddress());

        return studentEntity;
    }

    public static StudentRepresentationDto EntityToDto(StudentEntity studentEntity){
        StudentRepresentationDto studentRepresentationDto = new StudentRepresentationDto();

        studentRepresentationDto.setStudentName(studentEntity.getStudentName());
        studentRepresentationDto.setEmail(studentEntity.getEmail());
        studentRepresentationDto.setEnrolledCourse(studentEntity.getEnrolledCourse());
        studentRepresentationDto.setMobileNumber(studentEntity.getMobileNumber());
        studentRepresentationDto.setCountryCode(studentEntity.getCountryCode());
        studentRepresentationDto.setAddress(studentEntity.getAddress());

        return  studentRepresentationDto;
    }
}
