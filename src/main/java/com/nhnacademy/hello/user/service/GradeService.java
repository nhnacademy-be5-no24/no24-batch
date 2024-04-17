package com.nhnacademy.hello.user.service;


import com.nhnacademy.hello.user.dto.reponse.GradeDto;

/**
 * 등급(Grade) 서비스 입니다.
 *
 * @author : 김병주
 * @date : 2024-04-02
 */
public interface GradeService {
    /**
     * 특정 등급에 대한 조회를 위한 메소드 입니다.
     * @param id 등급 고유 번호
     * @return 등급 정보를 담은 Dto
     */
    GradeDto getGrade(Long id);
    /**
     * 특정 등급에 대한 생성을 위한 메소드 입니다.
     * @param gradeDto
     * @return 등급 정보를 담은 Dto
     */
    GradeDto createGrade(GradeDto gradeDto);
    /**
     * 특정 등급에 대한 수정을 위한 메소드 입니다.
     * @param id
     * @param gradeDto
     * @return 등급 정보를 담은 Dto
     */
    GradeDto modifyGrade(Long id, GradeDto gradeDto);
    /**
     * 특정 등급에 대한 삭제를 위한 메소드 입니다.
     * @param id
     * @return 등급 정보를 담은 Dto
     */
    GradeDto deleteGrade(Long id);

}
