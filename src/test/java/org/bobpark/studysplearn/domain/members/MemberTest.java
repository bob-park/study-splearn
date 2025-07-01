package org.bobpark.studysplearn.domain.members;

import static org.assertj.core.api.Assertions.*;
import static org.bobpark.studysplearn.domain.members.MemberFixture.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberTest {

    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        this.passwordEncoder = createPasswordEncoder();
    }

    @Test
    void createMember() {
        // given
        var member =
            Member.builder()
                .email("bobpark@bob.org")
                .nickname("bob")
                .password("abcde")
                .passwordEncoder(passwordEncoder)
                .build();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

}
