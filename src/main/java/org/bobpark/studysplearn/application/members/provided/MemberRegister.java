package org.bobpark.studysplearn.application.members.provided;

import org.bobpark.studysplearn.domain.members.Member;
import org.bobpark.studysplearn.domain.members.MemberRegisterRequest;

/**
 * 회원의 등록과 관련된 기능을 제공한다.
 */
public interface MemberRegister {
    Member register(MemberRegisterRequest registerRequest);
}
