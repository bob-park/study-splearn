package org.bobpark.studysplearn.domain.members;

import jakarta.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * hibernate 6.3 >= 시 record 에서도 @Embeddable 을 사용할 수 있지만, querydsl 를 사용할 경우 querydsl 은 아직 hibernate 버전이 업데이트 되지 않아 사용할 수 없음
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Email {

    private String address;

    public Email(String address) {
        this.address = address;
    }
}
