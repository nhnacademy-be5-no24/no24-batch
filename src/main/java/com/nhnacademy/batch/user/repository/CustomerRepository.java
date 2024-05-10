package com.nhnacademy.batch.user.repository;

import com.nhnacademy.batch.user.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByCustomerId(String customerId);
}
