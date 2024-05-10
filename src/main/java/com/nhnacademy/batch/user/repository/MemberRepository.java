package com.nhnacademy.batch.user.repository;

import com.nhnacademy.batch.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {
//    Page<MemberDto> findAllByGrade(Grade grade, Pageable pageable);

    List<Member> findByLastLoginAt(LocalDateTime time);
}
