package com.nhnacademy.hello.user.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @Column(name = "customer_no")
    private Long customerNo;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_no")
    private Customer customer;

    @Column(name = "member_id")
    private String memberId;
    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;
    @OneToOne
    @JoinColumn(name = "grade_id")
    private Grade grade;       //class 이름 사용안됨
    @Column(name = "role")
    private String role;

    @Column(name = "memberState")
    private String memberState;

}
