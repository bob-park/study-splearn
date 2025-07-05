package org.bobpark.studysplearn.adapter.webapi.member;

import static org.assertj.core.api.Assertions.*;

import java.io.UnsupportedEncodingException;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;
import org.springframework.test.web.servlet.assertj.MvcTestResultAssert;
import org.springframework.transaction.annotation.Transactional;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.bobpark.studysplearn.adapter.webapi.member.dto.MemberRegisterResponse;
import org.bobpark.studysplearn.application.members.required.MemberRepository;
import org.bobpark.studysplearn.domain.members.Member;
import org.bobpark.studysplearn.domain.members.MemberFixture;
import org.bobpark.studysplearn.domain.members.MemberRegisterRequest;
import org.bobpark.studysplearn.domain.members.PasswordEncoder;

/**
 * api test
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class MemberApiTest {

    final MockMvcTester mvcTester;
    final ObjectMapper om;
    final MemberRepository memberRepository;

    PasswordEncoder passwordEncoder = MemberFixture.createPasswordEncoder();

    @Test
    void register() throws JsonProcessingException, UnsupportedEncodingException {
        MemberRegisterRequest request =
            MemberRegisterRequest.builder()
                .email("bob@bob.org")
                .password("123456")
                .nickname("bobpark")
                .address("test")
                .build();

        String requestJson = om.writeValueAsString(request);

        MvcTestResult result =
            mvcTester.post().uri("/members")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson).exchange();

        assertThat(result)
            .hasStatusOk()
            .bodyJson()
            .hasPathSatisfying("$.memberId", value -> assertThat(value).isNotNull());

        // 실제 DB 에 들어간 값비교
        MemberRegisterResponse response =
            om.readValue(result.getResponse().getContentAsString(),
                MemberRegisterResponse.class);

        Member member = memberRepository.findById(response.memberId()).orElseThrow();

        assertThat(member.getId()).isNotNull();

    }

}
