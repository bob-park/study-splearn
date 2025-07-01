package org.bobpark.studysplearn.application.members.required;

import org.springframework.data.repository.Repository;

import org.bobpark.studysplearn.domain.members.Member;

/**
 * JpaRepository 를 사용하는게 아니라 maker interface 인 Repository 를 사용
 * <p>
 * jpa 에서 사용하는 named rule 를 따르면, spring data jpa 가 자동으로 메서드를 구현해준다
 * <p>
 * <p>
 * 회원 정보를 저장하거나 조회한다.
 */
public interface MemberRepository extends Repository<Member, Long> {

    Member save(Member member);

}
