package org.bobpark.studysplearn.adapter.webapi.member;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.bobpark.studysplearn.application.members.provided.MemberRegister;
import org.bobpark.studysplearn.domain.members.Member;
import org.bobpark.studysplearn.domain.members.MemberFixture;
import org.bobpark.studysplearn.domain.members.MemberRegisterRequest;
import org.bobpark.studysplearn.domain.members.PasswordEncoder;

/**
 * API 단위 테스트
 */
// ! 먼가 설정 문제인거 같긴한데, lombok 을 통해 생성자 주입으로 실행할경우 Autowired 가 실행되지 않음
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@WebMvcTest(MemberApi.class)
class MemberApiWebMvcTest {

    final MockMvcTester mvcTester;
    final ObjectMapper om;

    @MockitoBean
    MemberRegister memberRegister;

    PasswordEncoder passwordEncoder = MemberFixture.createPasswordEncoder();

    @Test
    void register() throws JsonProcessingException {

        Member member =
            Member.builder()
                .id(1L)
                .email("bob@bob.org")
                .password("123456")
                .passwordEncoder(passwordEncoder)
                .nickname("bob")
                .address("test")
                .build();

        when(memberRegister.register(any())).thenReturn(member);

        MemberRegisterRequest request =
            MemberRegisterRequest.builder()
                .email("bob@bob.org")
                .password("123456")
                .nickname("bob")
                .address("test")
                .build();

        String requestJson = om.writeValueAsString(request);

        assertThat(mvcTester.post().uri("/members")
            .contentType(MediaType.APPLICATION_JSON).content(requestJson))
            .hasStatusOk()
            .bodyJson()
            .extractingPath("$.memberId").asNumber().isEqualTo(1);

        verify(memberRegister).register(request);

    }
}
