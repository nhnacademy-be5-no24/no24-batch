package com.nhnacademy.batch.user.repository;

import com.nhnacademy.batch.user.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade,Long> {
    Grade deleteByGradeId(Long id);
}
