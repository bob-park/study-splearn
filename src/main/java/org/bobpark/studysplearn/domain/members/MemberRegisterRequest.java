package org.bobpark.studysplearn.domain.members;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import lombok.Builder;

@Builder
public record MemberRegisterRequest(@Email String email,
                                    @Size(min = 5, max = 10) String nickname,
                                    @Size(min = 5, max = 10) String password,
                                    String address) {
}
