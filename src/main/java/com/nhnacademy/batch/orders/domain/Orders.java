package com.nhnacademy.batch.orders.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nhnacademy.batch.user.entity.Customer;
import com.nhnacademy.batch.order_detail.domain.OrderDetail;
import com.nhnacademy.batch.payment.domain.Payment;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 주문(Order) 테이블.
 *
 * @author : 박동희
 * @date : 2024-04-01
 *
 **/
@Entity
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class Orders {

    public enum OrderState {
        COMPLETE_PAYMENT, WAITING, SHIPPING, COMPLETED, RETURNS, ORDER_CANCELED, PURCHASE_CONFIRMED
    }

    @Id
    private String orderId;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "ship_date", nullable = false)
    private LocalDate shipDate;

    @Column(name = "order_state", nullable = false)
    private OrderState orderState;

    @Column
    private Long totalFee;

    @Column(name = "delivery_fee", nullable = false)
    private int deliveryFee;

    @ManyToOne
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "customer_no", nullable = false)
    private Customer customer;

    @Column(name = "receiver_name", nullable = false)
    private String receiverName;

    @Column(name = "receiver_phone_number", nullable = false)
    private String receiverPhoneNumber;

    @Column(name = "zipcode", nullable = false)
    private String zipcode;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "address_detail", nullable = false)
    private String addressDetail;

    @Column(name = "req")
    private String req;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public Orders modifyState(OrderState orderState){
        this.orderState = orderState;
        return this;
    }


    public Orders setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;

        return this;
    }

}
