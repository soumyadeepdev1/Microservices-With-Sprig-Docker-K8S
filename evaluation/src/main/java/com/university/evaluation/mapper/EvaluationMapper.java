package com.university.evaluation.mapper;

import com.university.evaluation.dto.PerformanceRepresentationDto;
import com.university.evaluation.entity.StudentPerformaceEntity;

public class EvaluationMapper {
    public static PerformanceRepresentationDto EntityToDto(StudentPerformaceEntity entity){
        return new PerformanceRepresentationDto(
                entity.getEnrollmentNo(),
                entity.getPaperId(),
                entity.getGrade(),
                1
        );
    }

    public static StudentPerformaceEntity DtoToEntity(StudentPerformaceEntity entity,PerformanceRepresentationDto dto){
        entity.setEnrollmentNo(dto.getEnrollmentNo());
        entity.setPaperId(dto.getPapperId());
        entity.setGrade(dto.getGrade());

        return entity;
    }
}
