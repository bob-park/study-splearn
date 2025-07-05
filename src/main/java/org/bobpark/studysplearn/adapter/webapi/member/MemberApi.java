package org.bobpark.studysplearn.adapter.webapi.member;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.bobpark.studysplearn.adapter.webapi.member.dto.MemberRegisterResponse;
import org.bobpark.studysplearn.application.members.provided.MemberRegister;
import org.bobpark.studysplearn.domain.members.Member;
import org.bobpark.studysplearn.domain.members.MemberRegisterRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("members")
public class MemberApi {

    private final MemberRegister memberRegister;

    @PostMapping(path = "")
    public MemberRegisterResponse register(@RequestBody MemberRegisterRequest request) {
        Member member = memberRegister.register(request);

        return MemberRegisterResponse.builder()
            .memberId(member.getId())
            .build();
    }
}
