package com.nhnacademy.batch.order_detail.repository;

import com.nhnacademy.batch.order_detail.domain.OrderDetail;
import com.nhnacademy.batch.orders.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{
    List<OrderDetail> findByOrder(Orders order);
}
