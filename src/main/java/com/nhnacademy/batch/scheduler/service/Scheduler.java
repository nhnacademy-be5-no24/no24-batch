package com.nhnacademy.batch.scheduler.service;

import com.nhnacademy.batch.orders.domain.Orders;
import com.nhnacademy.batch.orders.repository.OrdersRepository;
import com.nhnacademy.batch.user.entity.Customer;
import com.nhnacademy.batch.user.entity.Grade;
import com.nhnacademy.batch.user.entity.Member;
import com.nhnacademy.batch.user.entity.MemberStateName;
import com.nhnacademy.batch.user.repository.CustomerRepository;
import com.nhnacademy.batch.user.repository.GradeRepository;
import com.nhnacademy.batch.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class Scheduler {
    private final MemberRepository memberRepository;
    private final CustomerRepository customerRepository;
    private final OrdersRepository ordersRepository;
    private final GradeRepository gradeRepository;


    /**
     * 24시간마다 휴면상태 회원 상태변경
     *
     * @Author : 김병주
     * @Date : 2024/04/27
     */
    @Scheduled(cron = "0 0 0 * * *")
    public void Loader() {
        log.warn("dormancy update");
        LocalDateTime todayDate = LocalDateTime.now().withSecond(0).withNano(0).minusMonths(6);

        List<Member> memberList = memberRepository.findByLastLoginAt(todayDate);
        if (memberList.isEmpty()) {
            return;
        }

        for (Member member : memberList) {
            Member modifiedMember = Member.builder()
                    .memberId(member.getMemberId())
                    .lastLoginAt(member.getLastLoginAt())
                    .role(member.getRole())
                    .customerNo(member.getCustomerNo())
                    .customer(member.getCustomer())
                    .grade(member.getGrade())
                    .memberState(MemberStateName.INACTIVE.toString())
                    .build();
            memberRepository.save(modifiedMember);
        }
    }

    /**
     * 1시간마다 결제내역 별 등급 수정
     *
     * @Author : 김병주
     * @Date : 2024/05/10
     */
    @Scheduled(cron = "0 0 * * * *")
    public void Loader1() {
        log.warn("grade update");
        List<Grade> grades = gradeRepository.findAll().stream()
                .sorted((a, b) -> (int) (a.getGradeId() - b.getGradeId()))
                .collect(Collectors.toList());

        List<Customer> customerList = customerRepository.findAll();

        for (Customer customer : customerList) {
            Optional<Member> optionalMember = memberRepository.findById(customer.getCustomerNo());
            if (optionalMember.isEmpty()) {
                continue;
            } else {
                Long userPayment = ordersRepository.findByCustomer(optionalMember.get().getCustomer()).stream()
                        .mapToLong(order -> order.getTotalFee())
                        .sum();
                Long gradeId = optionalMember.get().getGrade().getGradeId();

                for (int i = 0; i < grades.size(); i++) {
                    if (i == grades.size() - 1) {
                        if (grades.get(i).getRequiredPayment() <= userPayment) {
                            gradeId = grades.get(i).getGradeId();
                        }
                    } else {
                        if (grades.get(i).getRequiredPayment() <= userPayment && userPayment < grades.get(i + 1).getRequiredPayment()) {
                            gradeId = grades.get(i).getGradeId();
                        }
                    }
                }
                Grade grade = gradeRepository.findById(gradeId).get();

                Member member = optionalMember.get();
                member.setGrade(grade);

                memberRepository.save(member);
            }
        }
    }

    /**
     * 1시간마다 모든 주문에 대해 배송 상태 변경
     *
     * @author : 강병구
     * @date : 2024/05/15
     */
    @Scheduled(cron = " 0 0 * * * *")
    public void deliveryLoader() {
        log.warn("delivery update");
        List<Orders> ordersList = ordersRepository.findAll();

        for (Orders order : ordersList) {

            LocalDate shipDate = order.getShipDate();
            LocalDate now = LocalDate.now();

            long daysBetween = ChronoUnit.DAYS.between(shipDate, now);

            if (-2 <= daysBetween && daysBetween < -1) {
                ordersRepository.save(order.modifyState(Orders.OrderState.SHIPPING));
            }

            if (daysBetween >= 0 && daysBetween <= 1) {
                ordersRepository.save(order.modifyState(Orders.OrderState.COMPLETED));
            }

            if (daysBetween >= 3) {
                ordersRepository.save(order.modifyState(Orders.OrderState.PURCHASE_CONFIRMED));
            }
        }
    }
}
