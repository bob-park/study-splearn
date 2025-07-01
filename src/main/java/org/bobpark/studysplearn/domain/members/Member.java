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
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import com.malgn.common.entity.annotation.SnowflakeIdGenerateValue;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "members")
@NaturalIdCache // @Id 가 아닌 경우 persistence context 에 캐싱하지 않지만, 요거 쓰면, 캐싱됨
public class Member {

    @Id
    @SnowflakeIdGenerateValue
    private Long id;

    /*
     * business 적으로 필요한 ID 인경우 hibernate 에서 제공하는 NatureId 를 사용하면 유효성 체크를 해줌
     *
     * 실제 컬럼에 unique 제약 조건이 붙음
     */
    @Embedded
    @NaturalId
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
