package com.nhnacademy.batch.user.repository;

import com.nhnacademy.batch.user.dto.reponse.MemberDto;
import com.nhnacademy.batch.user.entity.Grade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author : 김병주
 * @date : 2024-04-02
 */
public interface MemberRepositoryCustom {
    Page<MemberDto> findMemberByGradeId(Grade grade, Pageable pageable);
}
