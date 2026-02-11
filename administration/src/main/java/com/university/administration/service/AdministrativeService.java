package com.university.administration.service;

import com.university.administration.dto.PerformanceDto;
import com.university.administration.dto.StudentAllInfoDto;
import com.university.administration.dto.StudentRepresentationDto;
import com.university.administration.dto.SuccessResponseDto;
import com.university.administration.entity.StudentEntity;
import com.university.administration.exception.StudentAlreadyExistsException;
import com.university.administration.exception.StudentDoesNotExistException;
import com.university.administration.feign.EvaluationFeignClient;
import com.university.administration.mapper.StudentMapper;
import com.university.administration.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdministrativeService {

    private StudentRepository studentRepository;

    private EvaluationFeignClient feignClient;


    public List<StudentAllInfoDto> getAllStudents(String corrId){



        return studentRepository.findAll()
                .stream()
                .map((ent)->{

                    StudentRepresentationDto dto = StudentMapper.EntityToDto(ent);
                    List<PerformanceDto> currentPerformance = List.of();
                    try{
                        currentPerformance = feignClient.getPerformanceByEnrollmentId(corrId,ent.getEnrollmentNo());
                    }
                    catch(Exception e){
                        System.err.println("Error fetching performance for enrollment " + ent.getEnrollmentNo() + ": " + e.getMessage());
                        currentPerformance = List.of();
                    }

                    return new StudentAllInfoDto(
                            dto.getStudentName(),
                            dto.getMobileNumber(),
                            dto.getEnrolledCourse(),
                            dto.getEmail(),
                            dto.getAddress(),
                            dto.getCountryCode(),
                            currentPerformance
                    );


                }).collect(Collectors.toList());
    }
    public SuccessResponseDto enrollStudent(StudentRepresentationDto newStudentDto){
        Optional<StudentEntity> existingStudent = studentRepository.findByMobileNumberOrEmail(
                newStudentDto.getMobileNumber(),
                newStudentDto.getEmail());
        if(existingStudent.isPresent()){
            throw new StudentAlreadyExistsException(
                    "Student Already exists with mobile : "+newStudentDto.getMobileNumber()+
                            " or email: "+newStudentDto.getEmail());
        }


        StudentEntity newStudentEntity = new StudentEntity();

        newStudentEntity.setEnrollmentNo("MAKAUT_"+new Date().getTime());

        newStudentEntity.setStudentName(newStudentDto.getStudentName());
        newStudentEntity.setEmail(newStudentDto.getEmail());
        newStudentEntity.setEnrolledCourse(newStudentDto.getEnrolledCourse());
        newStudentEntity.setMobileNumber(newStudentDto.getMobileNumber());
        newStudentEntity.setCountryCode(newStudentDto.getCountryCode());
        newStudentEntity.setAddress(newStudentDto.getAddress());

        studentRepository.save(newStudentEntity);

        return new SuccessResponseDto(
                "Student Details Saved Successfully",
                LocalDateTime.now());
    }
    public SuccessResponseDto updateStudent(StudentRepresentationDto existingStudent){

        StudentEntity existingEnity = studentRepository.findByMobileNumberOrEmail(
                existingStudent.getMobileNumber(),
                existingStudent.getEmail())
                .orElseThrow(()-> new StudentDoesNotExistException(
                        "Student does not exits with Mobile : "
                                + existingStudent.getMobileNumber() +
                                " or Email : "
                                + existingStudent.getEmail()
                ));

        studentRepository.save(StudentMapper.DtoToEntity(existingStudent,existingEnity));

        return new SuccessResponseDto(
                "Student Details updated Successfully",
                LocalDateTime.now());
    }
    public SuccessResponseDto unenrollStudent(StudentRepresentationDto existingStudent){
        StudentEntity existingEnity = studentRepository.findByMobileNumberOrEmail(
                        existingStudent.getMobileNumber(),
                        existingStudent.getEmail())
                .orElseThrow(()-> new StudentDoesNotExistException(
                        "Student does not exits with Mobile : "
                                + existingStudent.getMobileNumber() +
                                " or Email : "
                                + existingStudent.getEmail()
                ));
        studentRepository.delete(existingEnity);

        return new SuccessResponseDto(
                "Student Details deleted Successfully",
                LocalDateTime.now());
    }
}
