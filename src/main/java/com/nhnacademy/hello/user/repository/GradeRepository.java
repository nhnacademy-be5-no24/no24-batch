package com.nhnacademy.hello.user.repository;

import com.nhnacademy.hello.user.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade,Long> {
    Grade deleteByGradeId(Long id);
}
