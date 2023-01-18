# REST DOC 

> **spec**
>
> Java, SpringBoot, Gradle, Jpa, H2, Junit, MockMvc, SpringRestDocs
>
> **port** 
>
> 8080





## 1. 테스트 로직

> UserController

- 회원 조회(GET)  `/user`

  ```json
  {
      loginId : "asdfg0237"
  }
  ```

  

- 회원 가입(POST) `/user`

  ````json
  {
   "loginId" : "asdfg0237",
   "pwd": "1234",
   "email": "asdfg0237@naver.com",
   "phoneNumber" : "01050907845"
  }
  ````

  

## 2. 테스트 코드

> UserControllerTest

### 2-1 테스트 Method

- searchUser (GET TEST)
- createUser (POST TEST)

### 2-2 테스트 생성시  adoc 파일 생성 위치

- build/generated-snippets/UserController
  - join
  - search

### 2-3 생성된 adoc 스니펫 통합하여 문서 생성

- docs.asciidoc
  - index.adoc
  - adoc 문법링크

