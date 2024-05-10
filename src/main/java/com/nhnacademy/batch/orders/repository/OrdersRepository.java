package com.nhnacademy.batch.orders.repository;

import com.nhnacademy.batch.orders.domain.Orders;
import com.nhnacademy.batch.user.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, String>{
    List<Orders> findByCustomer(Customer customer);
}
