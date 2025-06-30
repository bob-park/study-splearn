# 다이어그램 일단 테스트

https://mermaid.js.org/syntax/entityRelationshipDiagram.html


* sequence
```mermaid


sequenceDiagram
    par 비밀번호 재설정
        autonumber
        client->>user-service: 사용자 비밀번호 재설정 요청
        user-service->>notification-service: 사용자 이메일로 "제설정 임시 링크" 전송 요청
        notification-service->>email: E-Mail 전송
    end


```

