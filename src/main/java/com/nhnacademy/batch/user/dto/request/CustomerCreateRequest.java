package com.nhnacademy.batch.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreateRequest {
    @NotBlank(message = "사용할 아이디를 입력해주세요")
    private String customerId;
    @NotBlank(message = "사용할 비밀번호를 입력해주세요")
    private String customerPassword;
    @NotBlank(message = "이름을 입력해주세요")
    private String customerName;
    private String customerPhoneNumber;
    @Email
    private String customerEmail;
    private LocalDate customerBirthday;

}
