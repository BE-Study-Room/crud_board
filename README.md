# 📋 Spring게시판

## 프로젝트 소개
- Spring 게시판 제작
  
## <a id="signup-page"></a> 결과 화면 


<details>
  <summary><b> 📃 2주차 내용</b></summary>
<div markdown="1">
    
  1. 추가 정보는 필요 없다고 판단 ➡️ 작성자 이름으로 변경

  2. entity 폴더 생성
      - [x] Board 생성 : id, title, content, writer 필드
  3. repository 폴더 생성  
      - [x] BoardRepository를 이용하여 다형성 활용 : create, findById, findByTitle, findAll, update, delete
      - [x] MemoryRepository로 구현체 생성 : 배열 대신 Map 사용, 등록 개수 제한 제거
  4. service 폴더 생성
      - [x] BoardService : 제목이 중복될 경우 예외 발생
  5. AppConfig 폴더 생성  
      - [x] IoC적용
      - [x] @Repository, @Service으로 자동 스캔하는 방법도 있지만 이번 주차에서는 Bean을 직접 등록하고 ApplicationContext를 사용해보았다.


  💾 [ ApplicationContext - Soyul블로그 ](https://soyulia.hashnode.dev/spring-applicationcontext)  
  💾 [ IoC와 DI - Soyul블로그 ](https://soyulia.hashnode.dev/spring-ioc-di)

</div>
</details>
