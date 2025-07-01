package org.bobpark.studysplearn.domain.members.entity;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberTest {

    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        this.passwordEncoder =
            new PasswordEncoder() {
                @Override
                public String encode(String password) {
                    return password.toUpperCase();
                }

                @Override
                public boolean matches(String password, String passwordHash) {
                    return encode(password).equals(passwordHash);
                }
            };
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
