# Youlpring 🌻🐝
### 율프링 - 스프링 의존성 없이 Spring을 기초부터 만들어보는 프로젝트🪺🕊
<br/>
* 톰캣, 서블릿 등을 만들어 보기 위해 초기 세팅만 우테코 미션 기본세팅을 참조하였습니다.<br/>
  - 초기 세팅 참고 코드 : https://github.com/woowacourse/jwp-dashboard-http <br/><br/><br/>


## 📍 실행 방법  
서버 시작 후 브라우저 창에 다음과 같이 입력: http://localhost:8080/index.html <br/><br/><br/>

## 🪜 프로젝트 진행 순서
[<img src="https://github.com/beatmeJY/Youlpring/assets/54700818/733c2b46-b61d-4b7b-9a2e-40976f5f6534"  width="650" height="380"/>](https://github.com/beatmeJY/Youlpring/blob/main/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8%20%EC%A7%84%ED%96%89%EC%88%9C%EC%84%9C.md)
<br/><br/><br/>
# 🌈 Branch 주요 진행내용 (PR)
## [Feat] 정적파일띄우기 ([#1](https://github.com/beatmeJY/Youlpring/pull/1))
### Socket을 열고 API 요청을 보내 InputStream 으로 데이터를 직접 뜯어보고, 
### 해당 내용을 담을 Request 와 Response 객체를 만든다.
#### - Request
 * Protocol / URI / Method 저장.
 * Hearder 정보 저장.
 * Body 정보 저장.
#### - Response
 * 응답 Header 세팅
 * View를 Body 객체에 저장 후 Byte 로 변환하여 반환 <br/><br/><br/>
  
## [Feat] 추상Contoller 만들기 ([#2](https://github.com/beatmeJY/Youlpring/pull/2))
### 추상 컨트롤러를 생성하고 이를 구현한 각 컨트롤러를 구현한다.
 - [상속과 구현의 차이점은?](https://github.com/beatmeJY/Youlpring/pull/2#discussion_r1443098448)
 - [과연 싱글톤 일까?](https://github.com/beatmeJY/Youlpring/pull/2#discussion_r1443093289) 
### 프론트 컨트롤러 패턴을 사용하여 이 컨트롤러들을 동적으로 호출하여 사용한다.
- [URL에 매핑된 컨트롤러가 없는 경우에는 어떻게 처리하면 좋을까?](https://github.com/beatmeJY/Youlpring/pull/2#discussion_r1443096258)
### [동적 페이지는 타임리프를 적용한다.](https://github.com/beatmeJY/Youlpring/pull/2/commits/0ce717690830d1e3437065eebc8a289ada14d748)
- 이 때 동적 페이지에 Model 을 사용하여 동적으로 바뀔 데이터도 전송하여 준다. <br/><br/><br/>
  
## [Refactor] 수정사항반영 ([#3](https://github.com/beatmeJY/Youlpring/pull/3))
### 대부분 메소드나 변수명을 보다 명확한 네이밍으로 수정. <br/><br/><br/>
  
## [Test] 테스트코드작성 ([#4](https://github.com/beatmeJY/Youlpring/pull/4))
### 미션 1단계의 Socket 과 관련된 코드들 모두 단위 테스트 작성.
[<img src="https://github.com/beatmeJY/Youlpring/assets/54700818/63075cc4-b969-4bd8-a126-899b30240345"  width="850" height="300"/>](https://github.com/beatmeJY/Youlpring/blob/main/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8%20%EC%A7%84%ED%96%89%EC%88%9C%EC%84%9C.md)
 - [Fixture 상속을 방지하려면?](https://github.com/beatmeJY/Youlpring/pull/4#discussion_r1531506869)
 - [반복코드는 ParameterizedTests 사용하기](https://github.com/beatmeJY/Youlpring/pull/4#discussion_r1531526117)
   - [반복코드 제거](https://github.com/beatmeJY/Youlpring/pull/4/commits/0372c7f45a94985b8d4475959417bfc39d7b9b21)
 - [Stubbing을 통해 실제 Socket 통신에서의 상황을 Unit 테스트 한다.](https://github.com/beatmeJY/Youlpring/blob/01990e31e7ae81b659e7410bee3cbe6a2ba9f035/src/test/java/com/youlpring/tomcat/apache/coyote/http11/request/HttpRequestTest.java#L64)
   <br/><br/><br/>

## [Test] 테스트코드작성 ([#5](https://github.com/beatmeJY/Youlpring/pull/5))
### 미션 2단계의 Controller, DB 관련된 코드들 모두 단위 테스트 작성.
![image](https://github.com/beatmeJY/Youlpring/assets/54700818/82cee254-311c-46dd-b8eb-8ea98606020e)
