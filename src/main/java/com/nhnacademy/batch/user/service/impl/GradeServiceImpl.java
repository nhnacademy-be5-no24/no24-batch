package com.nhnacademy.batch.user.service.impl;

import com.nhnacademy.batch.exception.GradeNotFoundException;
import com.nhnacademy.batch.user.dto.reponse.GradeDto;
import com.nhnacademy.batch.user.entity.Grade;
import com.nhnacademy.batch.user.repository.GradeRepository;
import com.nhnacademy.batch.user.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 등급(Grade) 서비스 구현체 입니다.
 *
 * @author : 김병주
 * @date : 2024-04-02
 */

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;

    @Override
    @Transactional(readOnly = true)
    public GradeDto getGrade(Long id) {
        Grade grade = gradeRepository.findById(id).orElseThrow(()->new GradeNotFoundException(id));
        return GradeDto.of(grade);
    }

    @Override
    @Transactional
    public GradeDto createGrade(GradeDto gradeDto) {
        Grade grade = gradeRepository.save(Grade.builder()
                .gradeName(gradeDto.getGradeName())
                .accumulateRate(gradeDto.getAccumulateRate())
                .build());
        return GradeDto.of(grade);
    }
    @Override
    @Transactional
    public GradeDto modifyGrade(Long id, GradeDto gradeDto) {
        Grade optionalGrade = gradeRepository.findById(id).orElseThrow(()->new GradeNotFoundException(id));

            Grade grade = gradeRepository.save(Grade.builder()
                    .gradeId(optionalGrade.getGradeId())
                    .gradeName(gradeDto.getGradeName())
                    .accumulateRate(gradeDto.getAccumulateRate())
                    .build());
            return GradeDto.of(grade);

    }
    @Override
    @Transactional
    public GradeDto deleteGrade(Long id) {
        Grade grade = gradeRepository.findById(id).orElseThrow(()->new GradeNotFoundException(id));
        return GradeDto.of(gradeRepository.deleteByGradeId(grade.getGradeId()));
    }
}
