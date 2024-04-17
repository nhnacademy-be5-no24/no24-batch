package com.nhnacademy.hello.user.controller;


import com.nhnacademy.hello.user.dto.reponse.CustomerDto;
import com.nhnacademy.hello.user.dto.request.CustomerCreateRequest;
import com.nhnacademy.hello.user.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 고객(Customer) RestController 입니다.
 *
 * @author : 김병주
 * @date : 2024-04-02
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class CustomerController {

    private final CustomerService customerService;
    /**
     * 고객 단건 조회 요청 시 사용되는 메소드입니다.
     *
     * @param customerNo 조회를 위한 해당 고객 id 입니다.
     * @return 성공했을 때 응답코드 200 OK 반환합니다.
     */
    @GetMapping("/customer/{customerNo}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable Long customerNo) {
        CustomerDto customerDto = customerService.getCustomer(customerNo);
        return ResponseEntity.ok(customerDto);
    }
    /**
     * 고객 생성 요청 시 사용되는 메소드입니다.
     *
     * @param customerCreateRequest 고객을 생성하기 위한 dto 입니다.
     * @return 성공했을 때 응답코드 200 OK 반환합니다.
     */
    @PostMapping("/customer/create")
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerCreateRequest customerCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customerCreateRequest));
    }
    /**
     * 고객 수정 요청 시 사용되는 메소드입니다.
     * @param id 고객이 존재하는지 확인을 위한 id 입니다.
     * @param customerCreateRequest 고객을 수정하기 위한 dto 입니다.
     * @return 성공했을 때 응답코드 200 OK 반환합니다.
     */
    @PutMapping("/customer/update/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id,@Valid @RequestBody CustomerCreateRequest customerCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.modifyCustomer(id, customerCreateRequest));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName=((FieldError)error).getField();
            String errorMessage=error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });
        return errors;
    }

}
