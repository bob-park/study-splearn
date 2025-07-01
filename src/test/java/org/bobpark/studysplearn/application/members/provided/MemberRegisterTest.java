package org.bobpark.studysplearn.application.members.provided;

import static org.assertj.core.api.Assertions.*;

import jakarta.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import org.junit.jupiter.api.Test;

import org.bobpark.studysplearn.configure.ApplicationTestConfiguration;
import org.bobpark.studysplearn.domain.members.Member;
import org.bobpark.studysplearn.domain.members.MemberRegisterRequest;

@SpringBootTest
@Transactional
@Import({ApplicationTestConfiguration.class})
class MemberRegisterTest {

    @Autowired
    MemberRegister memberRegister;

    @Test
    void register() {
        Member member =
            memberRegister.register(
                MemberRegisterRequest.builder()
                    .email("bobpark@bobpark.org")
                    .nickname("bobpark")
                    .password("12345")
                    .build());

        assertThat(member).isNotNull();
    }

    @Test
    void registerValidate() {

        MemberRegisterRequest registerRequest =
            MemberRegisterRequest.builder()
                .email("bobpark@bobpark.org")
                .nickname("bobpark")
                .password("1234")
                .build();

        assertThatThrownBy(() -> memberRegister.register(registerRequest))
            .isInstanceOf(ConstraintViolationException.class);

    }

}
