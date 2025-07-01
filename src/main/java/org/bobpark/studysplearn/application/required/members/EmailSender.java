package org.bobpark.studysplearn.application.required.members;

import org.bobpark.studysplearn.domain.members.Email;

public interface EmailSender {

    void send(Email email, String subject, String body);
}
