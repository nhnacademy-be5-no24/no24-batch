package com.nhnacademy.hello.user.dto.reponse;

import com.nhnacademy.hello.user.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
    private String customerId;
    private String customerPassword;
    private String customerName;
    private String customerPhoneNumber;
    private String customerEmail;
    private LocalDate customerBirthday;
    private String  customerRole;

    public static CustomerDto of(Customer customer) {
        return CustomerDto.builder()
                .customerId(customer.getCustomerId())
                .customerPassword(customer.getCustomerPassword())
                .customerName(customer.getCustomerName())
                .customerPhoneNumber(customer.getCustomerPhoneNumber())
                .customerEmail(customer.getCustomerEmail())
                .customerBirthday(customer.getCustomerBirthday())
                .customerRole(customer.getCustomerRole())
                .build();
    }
}
