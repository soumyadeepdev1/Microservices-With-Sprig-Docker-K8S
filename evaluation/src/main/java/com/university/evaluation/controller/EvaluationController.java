package com.university.evaluation.controller;


import com.university.evaluation.dto.PerformanceRepresentationDto;
import com.university.evaluation.dto.SuccessResponseDto;
import com.university.evaluation.service.EvaluationService;
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
@Validated
@AllArgsConstructor
public class EvaluationController {

    private EvaluationService evaluationService;
    private static final Logger logger = LoggerFactory.getLogger(EvaluationController.class);

    @GetMapping("/getAllEvaluation")
    public ResponseEntity<List<PerformanceRepresentationDto>> getAllStudentPerformanceData(){
        return new ResponseEntity<>(evaluationService.getAllEvaluation(), HttpStatus.OK);
    }
    @GetMapping("/getSpecificEvaluation")
    public ResponseEntity<List<PerformanceRepresentationDto>> getStudentPerformanceDataByEnrollmentId(@RequestHeader String corrId,@RequestParam String EnrollmentId){
        logger.debug("received correlationId from request : "+ corrId);
        return new ResponseEntity<>(evaluationService.getEvaluationByEnrollmentId(EnrollmentId), HttpStatus.OK);
    }

    @PostMapping("/createEvaluation")
    public ResponseEntity<SuccessResponseDto> createStudentEvaluation(@RequestBody @Valid PerformanceRepresentationDto performanceRepresentationDto){
        return new ResponseEntity<>(evaluationService.createEvaluation(performanceRepresentationDto),HttpStatus.OK);
    }
    @PatchMapping("/updateEvaluation")
    public ResponseEntity<SuccessResponseDto> updateStudentEvaluation(@RequestBody @Valid PerformanceRepresentationDto performanceRepresentationDto){
        return new ResponseEntity<>(evaluationService.updateEvaluation(performanceRepresentationDto),HttpStatus.OK);
    }
    @DeleteMapping("/deleteEvaluation")
    public ResponseEntity<SuccessResponseDto> deleteEvaluation(@RequestBody @Valid PerformanceRepresentationDto performanceRepresentationDto){
        return new ResponseEntity<>(evaluationService.deleteEvaluation(performanceRepresentationDto),HttpStatus.OK);
    }

}
