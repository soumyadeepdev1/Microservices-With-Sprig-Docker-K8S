package com.university.administration.controller;

import com.university.administration.dto.PerformanceDto;
import com.university.administration.dto.StudentAllInfoDto;
import com.university.administration.dto.StudentRepresentationDto;
import com.university.administration.dto.SuccessResponseDto;
import com.university.administration.enums.AvailableCourse;
import com.university.administration.enums.Grade;
import com.university.administration.service.AdministrativeService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Validated
public class AdministrationController {

    private final AdministrativeService administrativeService;

    private static final Logger logger = LoggerFactory.getLogger(AdministrationController.class);

    @RateLimiter(name = "getAllStudents",fallbackMethod = "getAllStudentsFallback")
    @GetMapping("/getAllStudents")
    public ResponseEntity<List<StudentAllInfoDto>> getAllStudents(@RequestHeader("wbut-webservice-correlation-id") String corrId){

        logger.debug("received correlationId from request : "+ corrId);

        return new ResponseEntity<>(
                administrativeService.getAllStudents(corrId), HttpStatus.OK
        );
    }

    public ResponseEntity<List<StudentAllInfoDto>> getAllStudentsFallback(@RequestHeader("wbut-webservice-correlation-id") String corrId,Throwable th){

        logger.debug("Within fallback method of getAllStudents");

        return new ResponseEntity<>(
                List.of(
                        new StudentAllInfoDto(
                                "N/A",
                                "N/A",
                                AvailableCourse.PASS,
                                "N/A",
                                "N/A",
                                "N/A",
                                List.of()




                        )
                ) , HttpStatus.OK
        );
    }

    @PostMapping("/enrollStudent")
    public ResponseEntity<SuccessResponseDto> enrollStudent(@RequestBody @Valid StudentRepresentationDto newStudentDto){
        return new ResponseEntity<>(administrativeService.enrollStudent(newStudentDto), HttpStatus.OK);
    }

    @PatchMapping("/updateStudent")
    public ResponseEntity<SuccessResponseDto> updateStudent(@RequestBody @Valid StudentRepresentationDto existingStudent){
        return new ResponseEntity<>(administrativeService.updateStudent(existingStudent),HttpStatus.OK);
    }

    @DeleteMapping("/deleteStudent")
    public ResponseEntity<SuccessResponseDto> unenrollStudent(@RequestBody @Valid StudentRepresentationDto existingStudent){
        return new ResponseEntity<>(administrativeService.unenrollStudent(existingStudent),HttpStatus.OK);
    }
}
