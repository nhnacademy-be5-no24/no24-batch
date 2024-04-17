package com.nhnacademy.hello.user.service.impl;


import com.nhnacademy.hello.exception.CustomerNotFoundException;
import com.nhnacademy.hello.user.dto.reponse.CustomerDto;
import com.nhnacademy.hello.user.dto.request.CustomerCreateRequest;
import com.nhnacademy.hello.user.entity.Customer;
import com.nhnacademy.hello.user.entity.Role;
import com.nhnacademy.hello.user.repository.CustomerRepository;
import com.nhnacademy.hello.user.service.CustomerService;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 고객(Customer) 서비스 입니다.
 *
 * @author : 김병주
 * @date : 2024-04-02
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional(readOnly = true)
    public CustomerDto getCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
        return CustomerDto.of(customer);
    }

    @Transactional
    @Override
    public CustomerDto createCustomer(CustomerCreateRequest customerCreateRequest) {
        String rawPassword = customerCreateRequest.getCustomerPassword();
        String encPassword = rawPassword;
        Customer customer = customerRepository.save(
                Customer.builder()
                        .customerId(customerCreateRequest.getCustomerId())
                        .customerPassword(encPassword)
                        .customerName(customerCreateRequest.getCustomerName())
                        .customerPhoneNumber(customerCreateRequest.getCustomerPhoneNumber())
                        .customerEmail(customerCreateRequest.getCustomerEmail())
                        .customerBirthday(customerCreateRequest.getCustomerBirthday())
                        .customerRole(Role.ROLE_CUSTOMER.toString()).build());
        return CustomerDto.of(customer);
    }

    @Transactional
    @Override
    public CustomerDto modifyCustomer(Long id, CustomerCreateRequest customerCreateRequest) {
        Customer optionalCustomer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
        String rawPassword = customerCreateRequest.getCustomerPassword();
        String encPassword =rawPassword;
        Customer customer = customerRepository.save(
                Customer.builder()
                        .customerNo(optionalCustomer.getCustomerNo())
                        .customerId(customerCreateRequest.getCustomerId())
                        .customerPassword(encPassword)
                        .customerName(customerCreateRequest.getCustomerName())
                        .customerPhoneNumber(customerCreateRequest.getCustomerPhoneNumber())
                        .customerEmail(customerCreateRequest.getCustomerEmail())
                        .customerBirthday(customerCreateRequest.getCustomerBirthday())
                        .customerRole(Role.ROLE_CUSTOMER.toString()).build());
        return CustomerDto.of(customer);
    }
}
