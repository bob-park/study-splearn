package org.bobpark.studysplearn.application.required.members;

import static org.assertj.core.api.Assertions.*;
import static org.bobpark.studysplearn.domain.members.MemberFixture.*;

import jakarta.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.bobpark.studysplearn.application.members.required.MemberRepository;
import org.bobpark.studysplearn.domain.members.Member;
import org.bobpark.studysplearn.domain.members.PasswordEncoder;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        this.passwordEncoder = createPasswordEncoder();
    }

    @Test
    void createMember() {
        Member createdMember =
            Member.builder()
                .email("bobpark@bobpark.org")
                .nickname("bob")
                .password("abcde")
                .passwordEncoder(passwordEncoder)
                .build();

        memberRepository.save(createdMember);

        em.flush();

    }

    @Test
    void duplicateEmailFail() {
        Member createdMember =
            Member.builder()
                .email("bobpark@bobpark.org")
                .nickname("bob")
                .password("abcde")
                .passwordEncoder(passwordEncoder)
                .build();

        memberRepository.save(createdMember);

        Member createdMember2 =
            Member.builder()
                .email("bobpark@bobpark.org")
                .nickname("bob")
                .password("abcde")
                .passwordEncoder(passwordEncoder)
                .build();

        assertThatThrownBy(() -> memberRepository.save(createdMember2))
            .isInstanceOf(DataIntegrityViolationException.class);
    }

}
