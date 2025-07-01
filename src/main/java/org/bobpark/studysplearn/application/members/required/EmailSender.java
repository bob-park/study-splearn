package org.bobpark.studysplearn.application.members.required;

public interface EmailSender {

    void send(org.bobpark.studysplearn.domain.shared.Email email, String subject, String body);
}
