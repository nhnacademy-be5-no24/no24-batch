package com.nhnacademy.hello.user.dto.reponse;

import com.nhnacademy.hello.user.entity.Grade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GradeDto {
    private String gradeName;
    private Long accumulateRate;

    public static GradeDto of(Grade grade) {
        return GradeDto.builder()
                .gradeName(grade.getGradeName())
                .accumulateRate(grade.getAccumulateRate())
                .build();
    }
}
