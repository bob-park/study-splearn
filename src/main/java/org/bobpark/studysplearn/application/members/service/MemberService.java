package org.bobpark.studysplearn.application.members.service;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import org.bobpark.studysplearn.application.members.provided.MemberRegister;
import org.bobpark.studysplearn.application.members.required.EmailSender;
import org.bobpark.studysplearn.application.members.required.MemberRepository;
import org.bobpark.studysplearn.domain.members.Member;
import org.bobpark.studysplearn.domain.members.MemberRegisterRequest;
import org.bobpark.studysplearn.domain.members.PasswordEncoder;

@Slf4j
@RequiredArgsConstructor
@Validated
@Service
@Transactional(readOnly = true)
public class MemberService implements MemberRegister {

    private final MemberRepository memberRepository;
    private final EmailSender emailSender;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Member register(@Valid MemberRegisterRequest registerRequest) {

        // check

        Member createdMember =
            Member.builder()
                .email(registerRequest.email())
                .nickname(registerRequest.nickname())
                .password(registerRequest.password())
                .passwordEncoder(passwordEncoder)
                .address(registerRequest.address())
                .build();

        createdMember = memberRepository.save(createdMember);

        log.debug("registered member. ({})", createdMember);


        emailSender.send(createdMember.getEmail(), "등록을 완료해주세요", "아래 링크를 클릭해서 등록을 완료해주세요.");

        return createdMember;
    }
}
