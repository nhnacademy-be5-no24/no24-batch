package com.nhnacademy.batch.user.controller;

import com.nhnacademy.batch.user.dto.reponse.GradeDto;
import com.nhnacademy.batch.user.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * 등급(Grade) RestController 입니다.
 *
 * @author : 김병주
 * @date : 2024-04-02
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class GradeController {
    private final GradeService gradeService;
    /**
     * 등급 단건 조회 요청 시 사용되는 메소드입니다.
     *
     * @param gradeId 조회를 위한 해당 고객 id 입니다.
     * @return 성공했을 때 응답코드 200 OK 반환합니다.
     */
    @GetMapping("/grade/{gradeId}")
    public ResponseEntity<GradeDto> getGrade(@PathVariable Long gradeId) {
        return ResponseEntity.ok().body(gradeService.getGrade(gradeId));
    }
    /**
     * 등급 생성 조회 요청 시 사용되는 메소드입니다.
     *
     * @param gradeDto 등급 생성하기 위한 dto 입니다.
     * @return 성공했을 때 응답코드 201 CREATED 반환합니다.
     */
    @PostMapping("/grade/create")
    public ResponseEntity<GradeDto> createGrade(@RequestBody GradeDto gradeDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gradeService.createGrade(gradeDto));
    }
    /**
     * 등급 수정 조회 요청 시 사용되는 메소드입니다.
     * @param id 등급이 존재하는지 확인하기 위한 id 입니다.
     * @param gradeDto 등급 수정하기 위한 dto 입니다.
     * @return 성공했을 때 응답코드 201 CREATED 반환합니다.
     */
    @PutMapping("/grade/{id}")
    public ResponseEntity<GradeDto> updateGrade(@PathVariable Long id, @RequestBody GradeDto gradeDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gradeService.modifyGrade(id, gradeDto));
    }
    /**
     * 등급 삭제 요청 시 사용되는 메소드입니다.
     * @param gradeId 등급을 삭제하기 위한 id 입니다.
     * @return 성공했을 때 응답코드 204 NO_CONTENT 반환합니다.
     */
    @DeleteMapping("/grade/{gradeId}")
    public ResponseEntity<GradeDto> deleteGrade(@PathVariable Long gradeId) {
        gradeService.deleteGrade(gradeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
