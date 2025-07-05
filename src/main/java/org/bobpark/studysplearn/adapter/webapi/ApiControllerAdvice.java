package org.bobpark.studysplearn.adapter.webapi;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import org.bobpark.studysplearn.domain.members.DuplicationEmailException;

/**
 * ResponseEntityExceptionHandler 는 왠만한 exception advice 를 가지고 있음
 * <p>
 * 추가로 필요한 exception handler 만 정의하면 됨
 */
@RestControllerAdvice
public class ApiControllerAdvice extends ResponseEntityExceptionHandler {

    /**
     * ProblemDetail 은 RFC 에서 정의한 api 표준 응답값을 파싱한 것
     * <p>
     * spring framework 제공해주기 때문에 더이상 error 에 대한 응답을 따로 준비할 이유가 없어짐
     *
     * @param e {@link DuplicationEmailException}
     * @return problemDetail
     */
    @ExceptionHandler(DuplicationEmailException.class)
    public ProblemDetail emailExceptionHandler(DuplicationEmailException e) {
        ProblemDetail result = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());

        result.setProperty("timestamp", LocalDateTime.now());
        result.setProperty("exception", e.getClass().getSimpleName());

        return result;
    }

}
