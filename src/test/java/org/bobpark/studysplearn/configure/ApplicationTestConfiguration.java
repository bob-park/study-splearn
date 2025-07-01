package org.bobpark.studysplearn.configure;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Fallback;

import org.bobpark.studysplearn.application.members.required.EmailSender;
import org.bobpark.studysplearn.domain.members.MemberFixture;
import org.bobpark.studysplearn.domain.members.PasswordEncoder;

@Slf4j
@TestConfiguration
public class ApplicationTestConfiguration {

    @Bean
    @Fallback // 아직 bean 이 등록되지 않을 경우 사용, 반대인 경우 무시
    public EmailSender emailSender() {
        return (email, subject, body) -> {
            log.debug("email={}, subject={}, body={}", email, subject, body);
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return MemberFixture.createPasswordEncoder();
    }
}
