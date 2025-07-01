package org.bobpark.studysplearn.domain.members.entity;

import static com.google.common.base.Preconditions.*;
import static org.apache.commons.lang3.StringUtils.*;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.springframework.lang.NonNull;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    private String email;
    private String nickname;
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    /**
     * @param email
     * @param nickname
     * @param password
     * @param passwordEncoder passwordEncoder 는 entity 에 저장되지 않아야함
     */
    @Builder
    private Member(@NonNull String email, @NonNull String nickname, @NonNull String password,
        @NonNull PasswordEncoder passwordEncoder) {

        checkArgument(isNotBlank(email), "email must be provided.");
        checkArgument(isNotBlank(password), "password must be provided.");
        checkArgument(ObjectUtils.isNotEmpty(passwordEncoder), "passwordEncoder must be provided.");


        this.email = email;
        this.nickname = nickname;
        this.passwordHash = passwordEncoder.encode(password);
        this.status = MemberStatus.PENDING;
    }
}
