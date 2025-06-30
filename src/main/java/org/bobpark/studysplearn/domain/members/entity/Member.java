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

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    private String email;
    private String nickname;
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @Builder
    private Member(String email, String nickname, String passwordHash, MemberStatus status) {

        checkArgument(isNotBlank(email), "email must be provided.");
        checkArgument(isNotBlank(passwordHash), "passwordHash must be provided.");

        this.email = email;
        this.nickname = nickname;
        this.passwordHash = passwordHash;
        this.status = MemberStatus.PENDING;
    }
}
