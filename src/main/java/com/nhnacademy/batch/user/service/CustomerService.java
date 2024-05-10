package com.nhnacademy.batch.user.service;



import com.nhnacademy.batch.user.dto.reponse.CustomerDto;
import com.nhnacademy.batch.user.dto.request.CustomerCreateRequest;

/**
 * 고객(Customer) 서비스 입니다.
 *
 * @author : 김병주
 * @date : 2024-04-02
 */
public interface CustomerService {
    /**
     * 특정 고객에 대한 조회를 위한 메소드 입니다.
     * @param id 고객 고유 번호
     * @return 고객 정보를 담은 Dto
     */
    CustomerDto getCustomer(Long id);
    /**
     * 특정 고객에 대한 생성을 위한 메소드 입니다.
     * @param customerCreateRequest
     * @return 고객 정보를 담은 Dto
     */
    CustomerDto createCustomer(CustomerCreateRequest customerCreateRequest);
    /**
     * 특정 고객에 대한 수정을 위한 메소드 입니다.
     * @param id
     * @param customerCreateRequest
     * @return 리뷰 정보를 담은 Dto
     */
    CustomerDto modifyCustomer(Long id, CustomerCreateRequest customerCreateRequest);

}
