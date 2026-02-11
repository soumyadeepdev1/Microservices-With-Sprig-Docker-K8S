package com.university.evaluation.service;


import com.university.evaluation.dto.PerformanceRepresentationDto;
import com.university.evaluation.dto.SuccessResponseDto;
import com.university.evaluation.entity.StudentPerformaceEntity;
import com.university.evaluation.exception.EvaluationAlreadyExistsException;
import com.university.evaluation.exception.EvaluationDoesNotExistException;
import com.university.evaluation.mapper.EvaluationMapper;
import com.university.evaluation.repository.EvaluationRepository;
import com.university.evaluation.utils.EvaluationIdGenerator;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EvaluationService {

    private EvaluationRepository evaluationRepository;

    public List<PerformanceRepresentationDto> getAllEvaluation(){


        return evaluationRepository.findAll()
                .stream()
                .map((PerformanceEntity)->{

                    String[] evaluationIdParts = PerformanceEntity.getEvaluationId().split("_");

                    return new PerformanceRepresentationDto(
                        PerformanceEntity.getEnrollmentNo(),
                        PerformanceEntity.getPaperId(),
                        PerformanceEntity.getGrade(),
                        Integer.parseInt(
                                evaluationIdParts[evaluationIdParts.length-1]
                        )

                    );
                }).collect(Collectors.toList());

    }

    public List<PerformanceRepresentationDto> getEvaluationByEnrollmentId(String enrollmentNo){

        List<StudentPerformaceEntity> entity =
                evaluationRepository
                        .findByEnrollmentNo(enrollmentNo).get();

        if(entity.isEmpty())
            throw new EvaluationDoesNotExistException("Evaluation does not exist for the student with enrollmentId: "+enrollmentNo);

        return entity.stream().map(EvaluationMapper::EntityToDto).collect(Collectors.toList());

    }

    public SuccessResponseDto createEvaluation(PerformanceRepresentationDto performanceRepresentationDto){

        Optional<StudentPerformaceEntity> performanceEntity =  evaluationRepository
                .findByEnrollmentNoAndEvaluationId(
                        performanceRepresentationDto.getEnrollmentNo(),
                        EvaluationIdGenerator.generateEvaluationId(
                                performanceRepresentationDto.getPapperId(),
                                performanceRepresentationDto.getEnrollmentNo(),
                                performanceRepresentationDto.getAttemptNo()
                        )
                );

        if(performanceEntity.isPresent())
            throw new EvaluationAlreadyExistsException( "Evaluation exists for the candidate with enrollment no : "+ performanceRepresentationDto.getEnrollmentNo());



        StudentPerformaceEntity entity = new StudentPerformaceEntity();
        entity.setEvaluationId(
                EvaluationIdGenerator.generateEvaluationId(
                        performanceRepresentationDto.getPapperId(),
                        performanceRepresentationDto.getEnrollmentNo(),
                        performanceRepresentationDto.getAttemptNo()
                )
        );
        evaluationRepository.save(EvaluationMapper.DtoToEntity(entity,performanceRepresentationDto));

        return new SuccessResponseDto("New Evaluation added Successfully",LocalDateTime.now());
    }

    public SuccessResponseDto updateEvaluation(PerformanceRepresentationDto performanceRepresentationDto){
        StudentPerformaceEntity performanceEntity =  evaluationRepository
                .findByEnrollmentNoAndEvaluationId(
                        performanceRepresentationDto.getEnrollmentNo(),
                        EvaluationIdGenerator.generateEvaluationId(
                                performanceRepresentationDto.getPapperId(),
                                performanceRepresentationDto.getEnrollmentNo(),
                                performanceRepresentationDto.getAttemptNo()
                        )
                ).orElseThrow(()-> new EvaluationDoesNotExistException(
                        "Given Evaluation does not exist for the candidate with enrollment no : "+ performanceRepresentationDto.getEnrollmentNo()));

        evaluationRepository.save(EvaluationMapper.DtoToEntity(performanceEntity,performanceRepresentationDto));

        return new SuccessResponseDto("Evaluation Updated SuccessFully", LocalDateTime.now());

    }

    public SuccessResponseDto deleteEvaluation(PerformanceRepresentationDto performanceRepresentationDto){
        StudentPerformaceEntity performanceEntity =  evaluationRepository
                .findByEnrollmentNoAndEvaluationId(
                        performanceRepresentationDto.getEnrollmentNo(),
                        EvaluationIdGenerator.generateEvaluationId(
                                performanceRepresentationDto.getPapperId(),
                                performanceRepresentationDto.getEnrollmentNo(),
                                performanceRepresentationDto.getAttemptNo()
                        )
                ).orElseThrow(()-> new EvaluationDoesNotExistException(
                        "Given Evaluation does not exist for the candidate with enrollment no : "+ performanceRepresentationDto.getEnrollmentNo()));


        evaluationRepository.delete(EvaluationMapper.DtoToEntity(performanceEntity,performanceRepresentationDto));

        return new SuccessResponseDto("Evaluation Deleted SuccessFully", LocalDateTime.now());
    }
}
