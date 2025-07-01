package org.bobpark.studysplearn.domain.members;

import org.springframework.lang.NonNull;

public interface PasswordEncoder {

    String encode(@NonNull String password);

    boolean matches(@NonNull String password, @NonNull String passwordHash);
}
