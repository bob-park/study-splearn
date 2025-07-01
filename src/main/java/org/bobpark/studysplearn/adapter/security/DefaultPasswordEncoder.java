package org.bobpark.studysplearn.adapter.security;

import lombok.extern.slf4j.Slf4j;

import org.springframework.lang.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import org.bobpark.studysplearn.domain.members.PasswordEncoder;

@Slf4j
@Component
public class DefaultPasswordEncoder implements PasswordEncoder {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String encode(@NonNull String password) {
        return encoder.encode(password);
    }

    @Override
    public boolean matches(@NonNull String password, @NonNull String passwordHash) {
        return encoder.matches(password, passwordHash);
    }
}
