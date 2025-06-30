package org.bobpark.studysplearn.domain.members.entity;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MemberTest {

    @Test
    void createMember(){
        // given
        var member =
            Member.builder()
                .email("bobpark@bob.org")
                .nickname("bob")
                .passwordHash("12345")
                .build();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

}
