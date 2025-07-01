# Splearn 개발 가이드

## 아키텍처
- 헥사고날 아키텍처
- 도메인 모델 패턴


### 계층
- Domain Layer
- Application Layer
- Adapter Layer

> 외부(Actor) -> 어댑터 -> 애플리케이션 -> 도메인


## 패키지 구조
```text
adapter/
    webapi/
      {version}/
    integration/
    security/
application/
  {names}/
    required/
    provided/
    service/
      {version}/
        {names}/
configure/
domain/
  {names}/
Application.java

```
- configure: 
- domain
  - {names}
- application
  - {names}
    - required: 내부와 상호작용하는 ports
    - provided: 외부와 상호작용하는 ports
    - service: provided interface 를 구현한 application service
      - {version}
        - {names}
- adapter
  - webapi
    - {version}
      - {names}
  - integration: 외부 시스템 연동
  - security: security 관련 (설정 제외)


