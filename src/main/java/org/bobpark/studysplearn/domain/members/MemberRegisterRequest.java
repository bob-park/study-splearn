package org.bobpark.studysplearn.domain.members;

import lombok.Builder;

@Builder
public record MemberRegisterRequest(String email,
                                    String nickname,
                                    String password) {
}
