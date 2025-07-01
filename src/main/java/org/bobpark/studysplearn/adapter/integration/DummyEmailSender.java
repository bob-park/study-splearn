package org.bobpark.studysplearn.adapter.integration;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import org.bobpark.studysplearn.application.members.required.EmailSender;

@Slf4j
@Component
public class DummyEmailSender implements EmailSender {

    @Override
    public void send(org.bobpark.studysplearn.domain.shared.Email email, String subject, String body) {
        log.debug("email={}, subject={}, body={}", email, subject, body);
    }
}
