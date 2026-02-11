package com.university.evaluation.repository;

import com.university.evaluation.entity.StudentPerformaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluationRepository extends JpaRepository<StudentPerformaceEntity,String> {
    Optional<StudentPerformaceEntity> findByEnrollmentNoAndEvaluationId(String enrollmentNo, String evaluationId);
    Optional<List<StudentPerformaceEntity>> findByEnrollmentNo(String enrollmentNo);
}
