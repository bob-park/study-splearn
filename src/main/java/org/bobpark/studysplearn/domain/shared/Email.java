package org.bobpark.studysplearn.domain.shared;

import jakarta.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * hibernate 6.3 >= 시 record 에서도 @Embeddable 을 사용할 수 있지만, querydsl 를 사용할 경우 querydsl 은 아직 hibernate 버전이 업데이트 되지 않아 사용할 수 없음
 */
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Email {

    private String email;

    public Email(String email) {
        this.email = email;
    }
}
