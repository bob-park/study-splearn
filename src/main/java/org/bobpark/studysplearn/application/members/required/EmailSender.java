package org.bobpark.studysplearn.application.members.required;

import org.bobpark.studysplearn.domain.members.Email;

public interface EmailSender {

    void send(Email email, String subject, String body);
}
