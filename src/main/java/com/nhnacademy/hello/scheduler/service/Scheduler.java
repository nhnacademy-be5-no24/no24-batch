package com.nhnacademy.hello.scheduler.service;

import com.nhnacademy.hello.user.entity.Member;
import com.nhnacademy.hello.user.entity.MemberStateName;
import com.nhnacademy.hello.user.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@Slf4j
@Transactional
public class Scheduler {
    @Autowired
    private MemberRepository memberRepository;
    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

    @Scheduled(cron = "0 0 0 * * *")
    public void Loader() {
        LocalDateTime todayDate = LocalDateTime.now().withSecond(0).withNano(0).minusMonths(6);
        log.info(todayDate.toString());
        logger.info("scheduler 반복 {}", LocalTime.now().withSecond(0).withNano(0));
        List<Member> memberList = memberRepository.findByLastLoginAt(todayDate);
        if (memberList.isEmpty()) {
            log.info("없음");
            return;
        }
        log.info("발견 !!!!!!!!!!!! {}",memberList);

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
}
