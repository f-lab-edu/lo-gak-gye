# lo-gak-gye

스마일게이트사의 RPG 게임 [로스트아크 Open API](https://developer-lostark.game.onstove.com/)를 활용하여 복잡한 계산을 자동화한 뒤, 캐릭터에 알맞은 각인 효율을 알려주는 프로젝트입니다.

## 프로젝트 목표

- 로스트아크 Open API를 활용한 각인 계산에 필요한 정보 자동화
- SOLID 원칙을 적용하여 변화에 유연하게 대처할 수 있는 확장성 있는 코드 작성
- GitHub Actions를 이용한 테스트 자동화, 도커 이미지 빌드 및 배포 자동화
- 분산 환경을 고려한 확장성 있는 애플리케이션 만들기
- APM 모니터링 도구 및 부하 테스트를 이용한 성능 개선

## 프로젝트 구조

![minkh-인프라 구성도 drawio](https://github.com/f-lab-edu/lo-gak-gye/assets/28918801/d23ad471-4bac-44ea-b70b-187b0efbbdbf)

## 사용 기술

- Java 17, Spring Boot 3.2.0, JPA, MySQL, Gradle, Docker, AWS

## 기술적 이슈 및 해결

- [GitHub Actions로 CI 구축](https://velog.io/@ksyj8256/GitHub-Actions%EB%A1%9C-CICD-%EA%B5%AC%EC%B6%95-1)
- [GitHub Actions로 CD 구축](https://velog.io/@ksyj8256/GitHub-Actions%EB%A1%9C-CICD-%EA%B5%AC%EC%B6%95-2)
- [Open API 호출에서 만난 응답 문제와 단위 테스트](https://velog.io/@ksyj8256/RestTemplate-%EB%8B%A8%EC%9C%84-%ED%85%8C%EC%8A%A4%ED%8A%B8-Mock)
- [전략 패턴과 의존성 주입을 활용한 확장성 있는 코드 작성](https://velog.io/@ksyj8256/%EB%82%B4%EA%B0%80-%ED%86%A0%EC%9D%B4-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8%EC%97%90-%EC%A0%84%EB%9E%B5-%ED%8C%A8%ED%84%B4%EC%9D%84-%EC%A0%81%EC%9A%A9%ED%95%9C-%EA%B3%BC%EC%A0%95)
- [애플리케이션 모니터링을 위한 APM 도입](https://velog.io/@ksyj8256/APM-%EB%8F%84%EC%9E%85-%EC%82%BD%EC%A7%88-%EC%9D%BC%EA%B8%B0Spring-Boot-Actuator-Prometheus-Grafana)
- [분산 서버 구축 후 세션 동기화 하기](https://velog.io/@ksyj8256/%ED%86%A0%EC%9D%B4-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8%EC%97%90%EC%84%9C-%EB%A1%9C%EB%93%9C%EB%B0%B8%EB%9F%B0%EC%84%9C-%EC%84%A4%EC%A0%95-%ED%9B%84-%EC%84%B8%EC%85%98-%EB%8F%99%EA%B8%B0%ED%99%94-%ED%95%98%EA%B8%B0)