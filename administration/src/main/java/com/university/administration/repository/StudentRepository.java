package com.university.administration.repository;

import com.university.administration.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity,String> {
     Optional<StudentEntity> findByMobileNumberOrEmail(String mobileNo,String email);


}
