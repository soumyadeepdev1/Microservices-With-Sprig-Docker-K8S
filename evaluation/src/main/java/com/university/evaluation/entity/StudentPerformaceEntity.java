package com.university.evaluation.entity;

import com.university.evaluation.enums.Grade;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student_performance")
public class StudentPerformaceEntity {

    @Id
    private String evaluationId;
    private String enrollmentNo;
    private String paperId;
    private Grade grade;


}
