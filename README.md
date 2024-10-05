## 쇼핑몰(Spring Boot + Thymeleaf + Springsecurity)

### ⚙개발 환경
- JAVA 11
- JDK 11
- **IDE** : Intellij   
- **Framework** : Spring boot 2.7
- **ORM** : Mybatis
- **Database** : Mysql 8.2
- **Library** :Thymeleaf, Springsecurity5, Jquery Ajax
- **Build Tool** : Gradle 8.5

### 🔑 프로젝트 실행 유의사항
**Spring 로그인 처리를 위해 유저등록(인증)은 Index("/") 접속시 DB에 등록하도록 구현했습니다.**

**ID :** test

**PASSWORD** : 1234

**Mysql Database Schema : shopping, Port: 3306**
1. 테이블 생성 SQL 스크립트 실행 
**(/resources/mybatis/Table.sql)**
2. 테스트 데이터 생성 SQL 스크립트 실행
**(/resources/mybatis/Insert.sql)**
3. 실행 어플리케이션 : ShoppingApplication
4. 접속 URL (http://localhost:8080/)
5. 상품 이미지, 상세 설명 이미지는 (static/images)폴더에 jpg 로 대체하였습니다.
  
### 📌주요 기능 
* 상품 리스트 ("/main")

  - DB에 등록된 상품리스트를 노출 : 대표이미지, 원가(할인 전 가격), 할인가, 할인율(%)
  - 찜 기능

    **로그인 유저** : UI 클릭으로 상품별 찜 등록/취소

    **비로그인** : UI 클릭 로그인 페이지 이동
    
  - 상품 10개씩 페이지네이션

* 상품 상세 ("/productView")

  - 상품코드(itemId)로 DB조회
  - 카테고리, 대표이미지, 제조사, 유통사, 상품명, 원가(할인 전 가격), 할인가, 할인율(%), 상품 상세설명
  - 찜 기능

    **로그인 유저** : UI 클릭으로 상품별 찜 등록/취소

    **비로그인** : UI 클릭 로그인 페이지 이동
      
* 찜 목록 ("/userLikeList")

  - 로그인 유저만 사용 가능
  - 로그인 유저가 찜 등록한 상품 리스트 노출 : 대표이미지, 원가(할인 전 가격), 할인가, 할인율(%)
  - 찜 취소 UI
### 🎄프로젝트 트리
```
   src
├─main                                
│├─java
││└─com
││  └─example
││    └─shopping
││      │ ShoppingApplication.java     #실행 어플리케이션
││      │ 
││      ├─config
││      │     SecurityConfig.java          #Spring Security 관련 설정(페이지 권한, Formlogin)
││      │      
││      ├─controller
││      │    MemberController.java         #유저 컨트롤러 (사용자등록, 로그인, 찜목록, 찜등록, 찜취소)
││      │    ProductController.java        #상품 컨트롤러 (상품목록, 상품상세)
││      │      
││      ├─dto
││      │    ItemDto.java                  #상품 Dto
││      │    LikeItem.java                 #찜아이템 Dto
││      │    Pagination.java               #페이지네이션 Dto
││      │    UserDto.java                  #유저 Dto
││      │    UserRole.java                 #유저 권한
││      │      
││      ├─handler
││      │    CustomAuthenticationFailureHandler.java  #로그인 실패 처리 Handler
││      │    CustomAuthenticationSuccessHandler.java  #로그인 성공 처리 Handler
││      │      
││      ├─mapper
││      │    ProductMapper.java                       #상품 Mapper
││      │    UserMapper.java                          #유저 Mapper
││      │      
││      └─service
││                CunstomUserDetailService.java       #Spring로그인 처리 Service
││                ProductService.java                 #상품 Service
││                ProductServiceImpl.java             #상품 ServiceImpl
││                UserService.java                    #유저 Service
││                UserServiceImpl.java                #유저 ServiceImpl
││                      
│└─resources
│ │ application.properties                           #스프링 관련 설정
│ │  
│ ├─mybatis
│ ││  Insert.sql                                     #초기 데이터 생성 SQL 스크립트
│ ││  mybatis-config.xml                             #Mybatis 관련 설정
│ ││  Table.sql                                      #테이블 생성 SQL 스크립트
│ ││  
│ │└─mapper
│ │        itemMapper.xml                            #상품 관련 쿼리 Mapper
│ │        userMapper.xml                            #유저 관련 쿼리 Mapper  
│ │        
│ ├─static
│ │├─css
│ ││      common.css                                 #Html css 처리
│ ││      
│ │└─images
│ │       icon_delete-item.svg                       #찜목록 취소 UI 이미지
│ │       icon_favorite.svg                          #찜 비활성화 UI 이미지
│ │       icon_favorite_on.svg                       #찜 활성화 UI 이미지
│ │       img1.jpg                                   #로컬 상품 이미지
│ │       img10.jpg                                  #로컬 상품 이미지    
│ │       img2.jpg                                   #로컬 상품 이미지
│ │       img3.jpg                                   #로컬 상품 이미지 
│ │       img4.jpg                                   #로컬 상품 이미지
│ │       img5.jpg                                   #로컬 상품 이미지
│ │       img6.jpg                                   #로컬 상품 이미지
│ │       img7.jpg                                   #로컬 상품 이미지
│ │       img8.jpg                                   #로컬 상품 이미지
│ │       img9.jpg                                   #로컬 상품 이미지
│ │       itemDetail.jpg                             #로컬 상품 상세설명 이미지 
│ │          
│ └─templates
│    ├─content
│    │    itemView.html                              #상품 상세보기 화면
│    │    loginForm.html                             #로그인 화면
│    │    main.html                                  #상품 목록 화면
│    │    userLikeList.html                          #찜 목록 화면
│    │      
│    ├─fragment
│    │    header.html                                #화면 상단 헤더
│    │      
│    └─layout
│           default_layout.html                      #화면 레이아웃
```

![image](https://github.com/user-attachments/assets/0e014154-a56f-4125-9b00-cfeb844cc4e8)

