# Youlpring 🌻🐝
### 율프링 - 스프링 의존성 없이 Spring을 기초부터 만들어보는 프로젝트🪺🕊

* 톰캣, 서블릿 등을 만들어 보기 위해 초기 세팅만 우테코 미션 기본세팅을 참조하였습니다.  
  - 초기 세팅 참고 코드 : https://github.com/woowacourse/jwp-dashboard-http


### 실행 환경 (현재 1. 정적파일띄우기 까지 진행)
    서버 시작 후 브라우저 창에 다음과 같이 입력: http://localhost:8080/index.html

# 프로젝트 진행 순서 (PR)
## 1. 정적파일띄우기 (톰캣 구현)
  - http://localhost:8080/index.html 입력 시 화면에 index.html 보여주기.
    - import 된 css, js도 전송해야함.
    - 요청과 응답 시 Request와 Response 객체 만들어야 함.
      - Request
        - protocol, URL, Method 저장.
        - URI QueryString 파싱하여 저장
        - HTTP Header 정보 파싱하여 저장. 
        - HTTP Body 저장.
      - Response
        - Header 첫번째 줄 세팅.(프로토콜, 응답코드)
        - index.html 등 파일 읽어서 Body에 저장.
        - 헤더 정보 수정
          - Context-type 파일에 따라 유동적으로 바꾸어 전송해야 함.
          - Context-Length 본문 바이트 길이로 저장.
    

## 2. Controller 만들기
  - frontController 패턴 구현.
    - 컨트롤러 인터페이스 만들어서 URL에 따른 다른 요청 처리.

## 3. Session 로그인 구현
  - 쿠키와 세션으로 간단한 로그인 기능 구현.

## 4. 멀티쓰레드 Thread Pool 적용
  - Thread Pool 로 효율적으로 Thread 관리.
