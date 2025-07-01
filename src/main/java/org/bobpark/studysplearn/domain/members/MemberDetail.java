package org.bobpark.studysplearn.domain.members;

import static com.google.common.base.Preconditions.*;
import static org.apache.commons.lang3.StringUtils.*;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.ToString.Exclude;

import org.springframework.lang.NonNull;

import com.malgn.common.entity.annotation.SnowflakeIdGenerateValue;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "")
public class MemberDetail {

    @Id
    @SnowflakeIdGenerateValue
    private Long id;

    @Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String address;

    private LocalDateTime createdDate;

    @Builder
    private MemberDetail(Long id, String address) {

        checkArgument(isNotBlank(address), "address must be provided.");

        this.id = id;
        this.address = address;
        this.createdDate = LocalDateTime.now();
    }

    public void setMember(@NonNull Member member) {
        this.member = member;
    }
}
