# 📋게임 정보 공유 게시판 프로젝트
# Office hours
- 2024년 2월 19일 월요일
    1. Property 설정 방식보다 yml 설정 방식을 선호하는 이유?
    - 중복 코드가 많다. > 가독성이 좋지 않음.
    - 리스트와 배열의 표현이 가독성이 떨어진다.
    - 실제 현업에서 spring, docker, amazon 등의 서비스 초기 설정 시 yml을 많이 사용한다.
    1. Hello World 출력에 있어 Controller 파트를 제외한 Service 파트 구현을 어떻게 해야할까요?
    - 단순히 Hello World 출력의 경우 비즈니스 로직을 사용하지 않기 때문에 현재 단계에서는 크게 신경 쓸 필요가 없고 이후 CRUD 구현에서 사용하게 될 것.
    1. Elice에서 Thymleaf에 관한 교육이 없는데 사전 지식이 필요할까요?
    - Thymleaf의 경우 사용해본 적이 없지만 Handlebars을 사용해본 경험은 있다. 두 템플릿 엔진은 사용방법이 미묘하게 다르지만 형식과 틀은 유사하기 때문에 Thymleaf의 공식 문서를 보면서 작동 원리를 이해한다면 크게 문제는 없을 것이고 백엔드 학습을 목적으로 하는 입장으로서 UI와 디자인은 크게 신경쓰지 않아도 된다.
    1. 스프링 의존성에서 JPA 추가 시 주의사항
    - 스프링 이니셜라이저의 의존성 JPA 추가 시 JDBC도 추가해주어야 하며 Spring에서 DB 연결을 자동으로 찾기 때문에 MySQL로 생성한 호스트에 대한 정보(url, username, password)를 application.properties 파일에 명시해주지 않으면 에러가 발생한다.
    1. SSR 방식과 CSR 방식에 대하여 소개
    - 현재 목표로 하고 있는 방식인 SSR 방식의 경우 @RestController를 사용하지 않고@Controller를 사용하고 Controller 파일에 작성된 메서드의 리턴 값으로 View name을 반환하는 방식으로서 resource 폴더의 templates에 존재하는 View name의 이름에 맞는 html 파일을 읽어오는 방식이다.
- 2024년 2월 21일 수요일
    
    ### 안내사항
    
    1. BoardRepository 파일의 Save 메서드에서 jdbcTemplate의 update 메서드 리턴값으로 0 아니면 1 이상의 where 절에 해당되는 컬럼의 개수 값이 반환되기 때문에 boardId로 받아오는 방식은 잘못되었고 Id값을 받아서 업데이트 하기 위해서는 KeyHolder를 사용한 방식을 사용하면 된다.
    2. Controller의 경우 API 방식이 아닌 HTML 파일의 이름을 반환해서 해당 되는 HTML 파일을 View에 띄워주는 방식이다.
    3. Controller 단에서는 Repository를 절대 사용해선 안 된다 그 이유는 JPA의 경우 Trasaction의 Pesistance(영속성)의 보장이 중요한데 Controller 계층에서는 이러한 영속성을 보장해줄 수 없기 때문이다.
    4. ERD는 반드시 작성해보는 것이 좋다. 왜냐하면 백엔드를 구현하면서 데이터베이스 정규화는 필수적으로 진행해야 하는 업무로서 ERD가 있을 시 정규화에 큰 도움이 된다.
    5. 백엔드 트랙을 선택했다면 view 보다 일단 백엔드에 집중하는 것이 바람직하다. 하지만 발표가 있기 때문에 기본적인 디자인은 부트 스트랩의 스타일을 활용하면 좋다.
    6. 백엔드 업무 특성상 일반적으로 프론트 엔드와 협업하는 경우가 많기 때문에 학습 시간이 남는다면 프론트엔드도 공부해보는 것이 좋다. 프론트엔드를 알고 있다면 API, DB, Scheme 작성에 도움이 많이 된다.
    7. 코드 리뷰에 있어서 Git의 이해도가 중요하다.
    - Local repository - staging area - remote repository
    - Staging - commit - push - fecth - pull
    - Branch - checkout - merge - conflict
    - 특히 코드리뷰를 받고 싶은 부분의 경우 최대 파일 20개를 넘지 않게 MR(Merge Request)을 진행하면 좋다.
    - 실제 업무에서는 Git Flow를 사용하여 구현할 기능별로 Branch 이름을 작성 후 나누어서 기능을 구현 후 다시 dev Branch에 Merge하는 것을 많이 활용하고 있다.
- 2024년 2월 23일 수요일
    
    ### 질의응답
    
    1. Post API Issue를 어떻게 해결하면 좋을까요?
    - 현재 게시판 생성 메서드에 사용되는 BoardDTO의 경우 불필요하게 id, writer와 같은 속성도 같이 받아오게 되어 있으나 실제 페이지에서는 title과 content만 입력 받아 전달되므로 게시판 생성을 위한 DTO를 별도로 구현할 필요가 있다.
    1. Domain의 Entity의 필드명과 DB의 컬럼명이 서로 다른 경우에는 어떻게 하나요?
    - 일반적으로는 필드명과 컬럼명을 맞춰주는 것을 권장하고 만약에 컬럼명 Snake case으로 작성되었고 필드명은 Camel case로 작성되었을 경우 @Column 어노테이션의 name 속성을 사용해서 DB 컬럼명을 명시한다면 해결이 가능하다.
    
    ### 안내사항
    
    1. 스프링 JPA를 Repository에서 사용 시에는 인터페이스로 구현된 추상메서드 명명 규칙을 정확하게 지켜서 사용할 필요가 있다.
    2. Repository에서 @Query 어노테이션 사용 시 변수 포맷을 지정을 여러 개 작성할 경우 동일한 포맷을 사용하지 않으면 에러가 발생하니 맞춰줄 필요가 있다.
    3. 실무에서 main branch의 경우 실제 사용자가 사용하는 Production 즉, 배포 버전이므로 commit과 push에는 주의를 기울여야 한다.
    4. dev branch에서 모든 것을 작업하는 것이 아닌 git flow를 이용한 기능별 branch를 작성하여 작업을 진행하는 습관을 들이는 것이 좋다. 왜냐하면 실제 업무와 비슷한 환경이고 dev branch에 많은 기능들이 한 꺼번에 개발되면 기능에 따른 코드 분리가 복잡해지기 때문이다.
    5. main branch에 merge를 할 경우에는 logs, build를 비롯한 불필요한 파일은 업로드 할 필요가 없기 때문에 .gitignore를 이용해서 커밋할 파일들을 제외 시켜놓는 것이 좋다.
    6. git confilct가 발생하는 이유는 부모 branch로부터 새로 생성한 branch의 코드 수정 작업 후 부모 branch의 내용이 수정되어 새로 생성했던 branch의 코드 라인과 겹치는 경우이다. 이를 해결하기 위해서는 변경된 부모 branch의 코드를 새로 생성했던 branch의 코드에 한번 merge를 해서 더 적절한 코드를 선택하여 충돌을 해결하고  다시 부모 branch로 merge하면 된다.
    - Example: dev(0) → dev-hello → dev(1) # merge 불가능
    - 해결 방법: dev(1) → dev-hello  # (conflict 충돌 → 코드 선택)→ dev(1)
    - 핵심 내용: 기존의 코드와 내가 작성한 코드를 정확히 이해해서 올바른 코드를 선택하면 문제가 되지 않는다.
    - conflict를 최대한 줄이려면 하루의 시작에 git pull과 git merge를 진행하여 dev branch의 수정사항을 미리 반영하고 새로운 branch를 생성하는 게 바람직하다.
- 2024년 2월 26일 월요일
    
    **질의응답**
    
    1. 참조 무결성 위배를 어떻게 해결하면 좋을까요?
    - EX) 학교(삭제) -> 학생(고아) => RDB 에러 발생
    
    *해결 방법*
    
    첫 번째: 학생 삭제 후 학교 삭제
    
    ---
    
    두 번째: 학교를 삭제할 시 소속된 학생 동시 삭제
    
    - DB 스키마를 이용한 방법 @Ondelet(action = OndeleteAction.CASCADE)를 이용한 방법
    - 영속성을 이용한 방법은 @OneToMany(cascade = CasecadeTypeALL, orphanRemoval = true)
    1. 현업에서 정렬 기능 구현 시 코드가 어떻게 되나요?
    - 2개로만 표현 가능한 경우 boolean을 사용하고 3개 이상의 경우 enum 클래스를 사용한다.
    
    ```java
    // 무신사 코드 예시
    // java => javascript
    enum class SortType{
    	new,
    	price_low,
    	price_high,
    	recommend,
    	...
    }
    ```
    
    1. 스프링 실행 후 다시 시작 시 DB가 초기화 되는 것을 어떻게 막나요?
    - spring data jpa 설정에서 ddl-auto: create 값을 를 주석처리 한다.
    
    **안내사항**
    
    1. 디버그 모드 사용법
    - 디버그 모드의 경우 VM을 사용해서 속도가 조금 느리다.
    - 데이터의 디버그 모드 사용 시에는 확인하고 싶은 코드의 상단에 중단점을 찍는다.
    - 스텝 오버: 이후 라인에 있는 코드 라인 하나씩 넘어감
    - 스텝 인투: 메서드가 있으면 계속해서 안으로 들어간다
    - 스텝 아웃: 내부 코드 실행 하나씩 박을 나온다.
    - 커서 위치로 이동: 다음 디버그 중단점까지 한 번에 이동
- 2024년 2월 28일 수요일
    
    ### 코드리뷰
    
    1. /static/hellobit.png 파일이 정상적으로 view에 반영되지 않는 이슈
    - Spring의 resource handler 설정을 통해서 경로 지정에 대해서 설정할 필요가 있다.
    - 해결방법: src: “/static/hellobit.png” → src: “hellobit.png”
    1. Window에서는 동작하는데 Mac에서는 동작 안하는 이슈 
    - 해결방법: Controller의 Pathvariable 속성에 name 설정, DTO 파일에 @Setter @NoArgsConstructor 추가해놓기
    
    ### 안내사항
    
    1. 레퍼런스 코드 기반으로 작성하는 사람의 경우 기존에 없는 다른 기능 1개 정도 추가해보자.
    2. Pagenation 기능의 경우 Frontend, Backend 상관없이 생각해볼 내용이 많기 때문에 꼭 구현해보고 이해한다.
    3. Controller에서 API만 미리 구현 해놓고 뷰페이지 없이 Postman으로 API가 정상 동작하는 지 미리 확인하는 경우가 많다.
    
- 2024년 3월 1일 금요일
    
    ### 질문사항
    
    1. 조회 수 기능 구현시 redirect 되면서 불필요한 조회수 증가 이슈
    - increatHits의 데이터를 view와 DB사이에서 어떤 기준을 근거로 입력 데이터를 어떻게 처리할지 정하는 것이 중요하다. 예를 들어 view에서 Boolean increaseHit에 대한 값을 Controller 계층에 넘겨 주면 if문을 이용해서 처리할 수 있다.
    1. 이미지 처리에 관해서
    - 현업에서 이미지는 절대로 서버, DB에 저장하지 않는다.
    - Blob을 이용해 DB에 저장하는 방식이 있으나 비효율적이라 사용하지 않고 로컬 환경에서 스스로 개발 시에는 사용할 수도 있다.
    - 실제 서비스 제공에 있어서 2가지의 방식이 존재하는데 일반적인 스타트업의 경우 이미지 처리까지 모두 관리하는 경우가 많고 어느 정도 규모가 있는 기업의 경우 IC 솔루션을 통해서 제 3자에게 이미지 처리 서비스를 맡기는 경우가 많다.
    1. 코틀린을 사용하는 이유
    - 자바의 경우 @Getter, @Setter등 외부 라이브러리에 의존하는 데에 비해서 코틀린은 그럴 필요가 없고 코드가 간결해지기 때문이다.
    
    ### 안내사항
    
    1. 게시판 프로젝트를 통해 얻어가야 하는 내용
    - Controller - Service - Repository의 Eco System에 대한 이해
    - HTML에서 보내는 Request 요청이 백엔드에서 Parameter로 어떻게 처리되는지 이해
    - 백엔드에서 Response로 보내는 데이터가 View에서 어떻게 처리될 것인가에 대한 이해
    - Repository(Jdbc template, JPA 등)에서 이루어지는 DB의 동작 방식에 대한 이해
    1. 반드시! Pagnation은 스스로 직접 구현해보자.
    - Pagnation 쿼리 스스로 작성 해보기
    - Scroll 방식도 next index, hasNext 형태로 구현할 수 있다.
    - 채팅의 경우 Token Pagnation을 사용한다.
    1. 좋은 개발자가 되기 위한 방법
    - 남의 코드를 많이 보는 것이 중요하다. 좋은 코드를 보고 내 코드에 반영시키는 것을 반복하다 보면 어느새 본인의 코드 역시 수준이 높은 코드가 되어 있다.
    - 시니어 개발자들은 주니어 개발자들의 코드를 보고 싶어한다. 왜냐하면 코드는 거짓말을 못하기 때문에 코드를 10~20줄 보았을 때 실력이 어느 정도인지 바로 파악이 되기 때문이다.
