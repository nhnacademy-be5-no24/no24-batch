package com.nhnacademy.hello.user.repository;

import com.nhnacademy.hello.user.dto.reponse.MemberDto;
import com.nhnacademy.hello.user.entity.Grade;
import com.nhnacademy.hello.user.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDateTime;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {
//    Page<MemberDto> findAllByGrade(Grade grade, Pageable pageable);

    List<Member> findByLastLoginAt(LocalDateTime time);
}
