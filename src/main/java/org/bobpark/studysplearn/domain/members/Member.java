package org.bobpark.studysplearn.domain.members;

import static com.google.common.base.Preconditions.*;
import static org.apache.commons.lang3.StringUtils.*;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.springframework.lang.NonNull;

import org.apache.commons.lang3.ObjectUtils;

import com.malgn.common.entity.annotation.SnowflakeIdGenerateValue;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "members")
public class Member {

    @Id
    @SnowflakeIdGenerateValue
    private Long id;

    @Embedded
    private Email email;

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

        this.email = new Email(email);
        this.nickname = nickname;
        this.passwordHash = passwordEncoder.encode(password);
        this.status = MemberStatus.PENDING;
    }
}
