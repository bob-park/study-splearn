package org.bobpark.studysplearn.domain.members;

import jakarta.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Email {

    private String address;

    public Email(String address) {
        this.address = address;
    }
}
