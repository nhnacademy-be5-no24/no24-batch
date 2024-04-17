package com.nhnacademy.hello.user.repository;

import com.nhnacademy.hello.user.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByCustomerId(String customerId);
}
