# SW마에스트로 14th 프로젝트 Coclimb

<img width="300" alt="image" src="https://github.com/JJBINY/coclimb-backend/assets/97151887/e0800ca6-0cdb-49a2-92d8-175b1c5cb450">

- 2023-06 ~ ing
<br/>  
<br/>



## 프로젝트 소개

- 암장 정보와 문제 풀이 획득을 단숨에, 클라이밍 생활을 윤택하게 만들어주는 클라이밍 정보 공유 플랫폼
![image](https://github.com/JJBINY/coclimb-backend/assets/97151887/d9f86389-685c-417a-b91e-02c7cb2bdf40)
![image](https://github.com/JJBINY/coclimb-backend/assets/97151887/6f8f7f0f-e05e-43ce-b6e3-57d035750b01)

<br/>  
<br/>


## Skills
- **BackEnd** : Java, Spring Framework, Docker
- **Database** : Mysql, Elasticsearch
- **Infra** : AWS EC2, RDS, S3, CloudFront, Secrete Manager
<br/>  
<br/>

## 주요 기능
- **API Docs** : https://api.co-climb.com
- **암장 지도** (개발 완료)
	- 지도 UI를 통해 클라이밍 매장(이하 암장)들의 대략적인 위치 및 분포를 손쉽게 확인할 수 있도록 제공
	- 지도 UI에 존재하는 암장을 선택하면, 해당 암장에 대한 요약 정보를 간단하게 확인

- **암장별 상세 페이지** (개발 중)
	- 암장에 대한 상세 정보를 제공
	-	암장 구독 시 암장관련 정보 푸시 알림
	-	사용자 리뷰 작성

- **등반 기록 및 공유** (개발 중)
	-	사용자가 자신의 등반 기록을 등록 및 공유
	-	주류 클라이밍 영상 저장소인 인스타그램의 API를 사용하여 손쉽게 사용자의 인스타그램 영상을 조회 및 저장 가능

- **문제 분류 및 추천** (개발 중)
  -	공유된 문제 영상을 암장, 문제 유형 등 카테고리 별로 분류
	-	사용자의 서비스 이용 이력을 통해 암장 추천 (등반 기록, 문제 영상 시청 기록 기반)

<br/>  
<br/>

## Cloud Architecture

![image](https://github.com/JJBINY/coclimb-backend/assets/97151887/6a5aa9e8-483c-4a85-91d8-66d0def5006a)
<br/>  
<br/>


## Directory Tree
<details>
<summary>src</summary>

``` bash
├── docs
│   └── asciidoc
│       ├── api
│       │   ├── error.adoc
│       │   ├── gym.adoc
│       │   ├── login.adoc
│       │   ├── media.adoc
│       │   ├── report.adoc
│       │   ├── search.adoc
│       │   └── user.adoc
│       └── index.adoc
├── main
│   ├── java
│   │   └── swm
│   │       └── s3
│   │           └── coclimb
│   │               ├── CoclimbApplication.java
│   │               ├── api
│   │               │   ├── adapter
│   │               │   │   ├── in
│   │               │   │   │   └── web
│   │               │   │   │       ├── gym
│   │               │   │   │       │   ├── GymController.java
│   │               │   │   │       │   └── dto
│   │               │   │   │       │       ├── GymCreateRequest.java
│   │               │   │   │       │       ├── GymLikeRequest.java
│   │               │   │   │       │       ├── GymLikesResponse.java
│   │               │   │   │       │       ├── GymLocationsResponse.java
│   │               │   │   │       │       ├── GymNameAutoCorrectResponse.java
│   │               │   │   │       │       ├── GymNearbyResponse.java
│   │               │   │   │       │       ├── GymPageResponse.java
│   │               │   │   │       │       ├── GymRemoveRequest.java
│   │               │   │   │       │       ├── GymSearchResponse.java
│   │               │   │   │       │       ├── GymUnlikeRequest.java
│   │               │   │   │       │       └── GymUpdateRequest.java
│   │               │   │   │       ├── login
│   │               │   │   │       │   ├── LoginController.java
│   │               │   │   │       │   └── dto
│   │               │   │   │       │       ├── LoginSuccessResponse.java
│   │               │   │   │       │       ├── OAuthLoginPageResponse.java
│   │               │   │   │       │       └── OAuthLoginRequest.java
│   │               │   │   │       ├── media
│   │               │   │   │       │   ├── MediaController.java
│   │               │   │   │       │   └── dto
│   │               │   │   │       │       ├── InstagramMyVideosResponse.java
│   │               │   │   │       │       ├── MediaCreateInstagramInfo.java
│   │               │   │   │       │       ├── MediaCreateProblemInfo.java
│   │               │   │   │       │       ├── MediaCreateRequest.java
│   │               │   │   │       │       ├── MediaInfoResponse.java
│   │               │   │   │       │       ├── MediaPageResponse.java
│   │               │   │   │       │       ├── MediaUpdateRequest.java
│   │               │   │   │       │       └── S3AccessTokenResponse.java
│   │               │   │   │       ├── report
│   │               │   │   │       │   ├── ReportController.java
│   │               │   │   │       │   └── dto
│   │               │   │   │       │       └── ReportCreateRequest.java
│   │               │   │   │       ├── search
│   │               │   │   │       │   ├── SearchController.java
│   │               │   │   │       │   └── dto
│   │               │   │   │       │       └── SearchNameResponse.java
│   │               │   │   │       └── user
│   │               │   │   │           ├── UserController.java
│   │               │   │   │           └── dto
│   │               │   │   │               └── UserInfoResponse.java
│   │               │   │   └── out
│   │               │   │       ├── aws
│   │               │   │       │   ├── AwsCloudFrontManager.java
│   │               │   │       │   ├── AwsS3Manager.java
│   │               │   │       │   ├── AwsSTSManager.java
│   │               │   │       │   └── dto
│   │               │   │       │       └── S3AccessToken.java
│   │               │   │       ├── filedownload
│   │               │   │       │   └── FileDownloader.java
│   │               │   │       ├── oauth
│   │               │   │       │   ├── instagram
│   │               │   │       │   │   ├── InstagramOAuthRecord.java
│   │               │   │       │   │   ├── InstagramRestApi.java
│   │               │   │       │   │   ├── InstagramRestApiManager.java
│   │               │   │       │   │   └── dto
│   │               │   │       │   │       ├── InstagramMediaResponseDto.java
│   │               │   │       │   │       ├── LongLivedTokenResponse.java
│   │               │   │       │   │       └── ShortLivedTokenResponse.java
│   │               │   │       │   └── kakao
│   │               │   │       │       ├── KakaoOAuthRecord.java
│   │               │   │       │       ├── KakaoRestApi.java
│   │               │   │       │       ├── KakaoRestApiManager.java
│   │               │   │       │       └── dto
│   │               │   │       │           └── KakaoTokenResponse.java
│   │               │   │       └── persistence
│   │               │   │           ├── gym
│   │               │   │           │   ├── GymDocumentRepository.java
│   │               │   │           │   ├── GymJpaRepository.java
│   │               │   │           │   ├── GymRepository.java
│   │               │   │           │   └── dto
│   │               │   │           │       └── GymNearby.java
│   │               │   │           ├── gymlike
│   │               │   │           │   ├── GymLikeJpaRepository.java
│   │               │   │           │   └── GymLikeRepository.java
│   │               │   │           ├── media
│   │               │   │           │   ├── MediaJpaRepository.java
│   │               │   │           │   └── MediaRepository.java
│   │               │   │           ├── report
│   │               │   │           │   ├── ReportJpaRepository.java
│   │               │   │           │   └── ReportRepository.java
│   │               │   │           ├── search
│   │               │   │           │   ├── ElasticsearchQueryFactory.java
│   │               │   │           │   ├── SearchManager.java
│   │               │   │           │   └── dto
│   │               │   │           │       └── AutoCompleteNameDto.java
│   │               │   │           └── user
│   │               │   │               ├── UserDocumentRepository.java
│   │               │   │               ├── UserJpaRepository.java
│   │               │   │               └── UserRepository.java
│   │               │   ├── application
│   │               │   │   ├── port
│   │               │   │   │   ├── in
│   │               │   │   │   │   ├── gym
│   │               │   │   │   │   │   ├── GymCommand.java
│   │               │   │   │   │   │   ├── GymQuery.java
│   │               │   │   │   │   │   └── dto
│   │               │   │   │   │   │       ├── GymCreateRequestDto.java
│   │               │   │   │   │   │       ├── GymInfoResponseDto.java
│   │               │   │   │   │   │       ├── GymLikeRequestDto.java
│   │               │   │   │   │   │       ├── GymLikesResponseDto.java
│   │               │   │   │   │   │       ├── GymLocationResponseDto.java
│   │               │   │   │   │   │       ├── GymNearbyResponseDto.java
│   │               │   │   │   │   │       ├── GymPageRequestDto.java
│   │               │   │   │   │   │       ├── GymSearchResponseDto.java
│   │               │   │   │   │   │       ├── GymUnlikeRequestDto.java
│   │               │   │   │   │   │       └── GymUpdateRequestDto.java
│   │               │   │   │   │   ├── login
│   │               │   │   │   │   │   └── LoginCommand.java
│   │               │   │   │   │   ├── media
│   │               │   │   │   │   │   ├── MediaCommand.java
│   │               │   │   │   │   │   ├── MediaQuery.java
│   │               │   │   │   │   │   └── dto
│   │               │   │   │   │   │       ├── MediaCreateRequestDto.java
│   │               │   │   │   │   │       ├── MediaDeleteRequestDto.java
│   │               │   │   │   │   │       ├── MediaInfoDto.java
│   │               │   │   │   │   │       ├── MediaPageRequestDto.java
│   │               │   │   │   │   │       └── MediaUpdateRequestDto.java
│   │               │   │   │   │   ├── report
│   │               │   │   │   │   │   ├── ReportCommand.java
│   │               │   │   │   │   │   ├── ReportQuery.java
│   │               │   │   │   │   │   └── dto
│   │               │   │   │   │   │       └── ReportCreateRequestDto.java
│   │               │   │   │   │   ├── search
│   │               │   │   │   │   │   ├── SearchQuery.java
│   │               │   │   │   │   │   └── dto
│   │               │   │   │   │   │       └── SearchNameResult.java
│   │               │   │   │   │   └── user
│   │               │   │   │   │       ├── UserCommand.java
│   │               │   │   │   │       └── UserQuery.java
│   │               │   │   │   └── out
│   │               │   │   │       ├── SearchPort.java
│   │               │   │   │       ├── aws
│   │               │   │   │       │   ├── AwsS3UpdatePort.java
│   │               │   │   │       │   └── dto
│   │               │   │   │       │       └── S3AccessToken.java
│   │               │   │   │       ├── filedownload
│   │               │   │   │       │   ├── DownloadedFileDetail.java
│   │               │   │   │       │   └── FileDownloadPort.java
│   │               │   │   │       ├── oauth
│   │               │   │   │       │   ├── instagram
│   │               │   │   │       │   │   ├── InstagramAuthPort.java
│   │               │   │   │       │   │   └── InstagramDataPort.java
│   │               │   │   │       │   └── kakao
│   │               │   │   │       │       ├── KakaoAuthPort.java
│   │               │   │   │       │       └── KakaoDataPort.java
│   │               │   │   │       └── persistence
│   │               │   │   │           ├── gym
│   │               │   │   │           │   ├── GymLoadPort.java
│   │               │   │   │           │   └── GymUpdatePort.java
│   │               │   │   │           ├── gymlike
│   │               │   │   │           │   ├── GymLikeLoadPort.java
│   │               │   │   │           │   └── GymLikeUpdatePort.java
│   │               │   │   │           ├── media
│   │               │   │   │           │   ├── MediaLoadPort.java
│   │               │   │   │           │   └── MediaUpdatePort.java
│   │               │   │   │           ├── report
│   │               │   │   │           │   ├── ReportLoadPort.java
│   │               │   │   │           │   └── ReportUpdatePort.java
│   │               │   │   │           └── user
│   │               │   │   │               ├── UserLoadPort.java
│   │               │   │   │               └── UserUpdatePort.java
│   │               │   │   └── service
│   │               │   │       ├── GymService.java
│   │               │   │       ├── LoginService.java
│   │               │   │       ├── MediaService.java
│   │               │   │       ├── ReportService.java
│   │               │   │       ├── SearchService.java
│   │               │   │       └── UserService.java
│   │               │   └── exception
│   │               │       ├── ErrorResponse.java
│   │               │       ├── ExceptionControllerAdvice.java
│   │               │       ├── FieldErrorType.java
│   │               │       └── errortype
│   │               │           ├── ValidationFail.java
│   │               │           ├── aws
│   │               │           │   ├── LocalFileDeleteFail.java
│   │               │           │   ├── S3DeleteFail.java
│   │               │           │   └── S3UploadFail.java
│   │               │           ├── basetype
│   │               │           │   ├── BadRequest.java
│   │               │           │   ├── Conflict.java
│   │               │           │   ├── CustomException.java
│   │               │           │   ├── Forbidden.java
│   │               │           │   ├── InternalServerError.java
│   │               │           │   ├── NotFound.java
│   │               │           │   └── Unauthorized.java
│   │               │           ├── elasticsearch
│   │               │           │   └── InvalidElasticQuery.java
│   │               │           ├── filedownload
│   │               │           │   └── FileDownloadFail.java
│   │               │           ├── gym
│   │               │           │   ├── GymNameConflict.java
│   │               │           │   └── GymNotFound.java
│   │               │           ├── gymlike
│   │               │           │   ├── AlreadyLikedGym.java
│   │               │           │   └── GymLikeNotFound.java
│   │               │           ├── instagram
│   │               │           │   ├── GetInstagramUsernameFail.java
│   │               │           │   ├── InvalidInstagramCodeFail.java
│   │               │           │   ├── IssueInstagramLongLivedTokenFail.java
│   │               │           │   ├── IssueInstagramShortLivedTokenFail.java
│   │               │           │   ├── RefreshInstagramTokenFail.java
│   │               │           │   └── RetrieveInstagramMediaFail.java
│   │               │           ├── internal
│   │               │           │   ├── ClassNotFound.java
│   │               │           │   └── MethodNotFound.java
│   │               │           ├── kakao
│   │               │           │   ├── IssueKakaoTokenFail.java
│   │               │           │   └── RetrieveKakaoUserInfoFail.java
│   │               │           ├── login
│   │               │           │   ├── AlreadyLogin.java
│   │               │           │   └── InvalidToken.java
│   │               │           ├── media
│   │               │           │   ├── InstagramMediaIdConflict.java
│   │               │           │   ├── MediaNotFound.java
│   │               │           │   └── MediaOwnerNotMatched.java
│   │               │           └── user
│   │               │               ├── UserAlreadyExists.java
│   │               │               └── UserNotFound.java
│   │               ├── config
│   │               │   ├── AppConfig.java
│   │               │   ├── AwsConfig.java
│   │               │   ├── DockerComposeCloser.java
│   │               │   ├── ElasticsearchConfig.java
│   │               │   ├── JpaAuditingConfig.java
│   │               │   ├── ServerClock.java
│   │               │   ├── WebConfig.java
│   │               │   ├── argumentresolver
│   │               │   │   ├── LoginUser.java
│   │               │   │   └── LoginUserArgumentResolver.java
│   │               │   ├── aspect
│   │               │   │   └── logtrace
│   │               │   │       ├── LogTrace.java
│   │               │   │       ├── LogTraceAspect.java
│   │               │   │       ├── LogTraceImpl.java
│   │               │   │       ├── NoLog.java
│   │               │   │       ├── TraceId.java
│   │               │   │       └── TraceStatus.java
│   │               │   ├── interceptor
│   │               │   │   ├── Auth.java
│   │               │   │   └── AuthInterceptor.java
│   │               │   ├── propeties
│   │               │   │   ├── AwsCloudFrontProperties.java
│   │               │   │   ├── AwsCredentialsProperties.java
│   │               │   │   ├── AwsRdsProperties.java
│   │               │   │   ├── AwsS3MediaProperties.java
│   │               │   │   ├── ElasticProperties.java
│   │               │   │   └── JwtProperties.java
│   │               │   └── security
│   │               │       └── JwtManager.java
│   │               └── domain
│   │                   ├── BaseTimeEntity.java
│   │                   ├── document
│   │                   │   ├── Document.java
│   │                   │   ├── DocumentClassMap.java
│   │                   │   ├── GymDocument.java
│   │                   │   └── UserDocument.java
│   │                   ├── gym
│   │                   │   ├── Gym.java
│   │                   │   └── Location.java
│   │                   ├── gymlike
│   │                   │   └── GymLike.java
│   │                   ├── media
│   │                   │   ├── InstagramMediaInfo.java
│   │                   │   ├── Media.java
│   │                   │   └── MediaProblemInfo.java
│   │                   ├── report
│   │                   │   └── Report.java
│   │                   └── user
│   │                       ├── InstagramUserInfo.java
│   │                       ├── KakaoUserInfo.java
│   │                       └── User.java
│   └── resources
│       ├── application-dev.yml
│       ├── application-local.yml
│       ├── application-release.yml
│       ├── application-secret.yml
│       ├── application-test.yml
│       ├── application.yml
│       └── static
│           └── index.html
└── test
    ├── java
    │   └── swm
    │       └── s3
    │           └── coclimb
    │               ├── CoclimbApplicationTests.java
    │               ├── api
    │               │   ├── ControllerTestSupport.java
    │               │   ├── IntegrationTestSupport.java
    │               │   ├── RestDocsTestSupport.java
    │               │   ├── adapter
    │               │   │   ├── in
    │               │   │   │   └── web
    │               │   │   │       ├── gym
    │               │   │   │       │   └── GymControllerTest.java
    │               │   │   │       ├── login
    │               │   │   │       │   └── LoginControllerTest.java
    │               │   │   │       ├── media
    │               │   │   │       │   └── MediaControllerTest.java
    │               │   │   │       ├── report
    │               │   │   │       │   └── ReportControllerTest.java
    │               │   │   │       ├── search
    │               │   │   │       │   └── SearchControllerTest.java
    │               │   │   │       └── user
    │               │   │   │           └── UserControllerTest.java
    │               │   │   └── out
    │               │   │       ├── aws
    │               │   │       │   ├── AwsCloudFrontManagerTest.java
    │               │   │       │   └── AwsSTSManagerTest.java
    │               │   │       ├── instagram
    │               │   │       │   └── InstagramRestApiManagerTest.java
    │               │   │       └── persistence
    │               │   │           ├── gym
    │               │   │           │   └── GymRepositoryTest.java
    │               │   │           ├── gymlike
    │               │   │           │   └── GymLikeRepositoryTest.java
    │               │   │           ├── media
    │               │   │           │   └── MediaRepositoryTest.java
    │               │   │           ├── search
    │               │   │           │   └── SearchManagerTest.java
    │               │   │           └── user
    │               │   │               └── UserRepositoryTest.java
    │               │   ├── application
    │               │   │   └── service
    │               │   │       ├── GymServiceTest.java
    │               │   │       ├── LoginServiceTest.java
    │               │   │       ├── MediaServiceTest.java
    │               │   │       ├── ReportServiceTest.java
    │               │   │       ├── SearchServiceTest.java
    │               │   │       └── UserServiceTest.java
    │               │   └── docs
    │               │       ├── ExceptionControllerDocsTest.java
    │               │       ├── GymControllerDocsTest.java
    │               │       ├── LoginControllerDocsTest.java
    │               │       ├── MediaControllerDocsTest.java
    │               │       ├── ReportControllerDocsTest.java
    │               │       ├── SearchControllerDocsTest.java
    │               │       └── UserControllerDocsTest.java
    │               ├── docker
    │               │   ├── CommandProcessor.java
    │               │   ├── DockerComposeRunner.java
    │               │   └── DockerContainerName.java
    │               ├── domain
    │               │   └── gym
    │               │       └── GymTest.java
    │               ├── learning
    │               │   ├── AwsCloudFrontLearningTest.java
    │               │   ├── AwsS3LearningTest.java
    │               │   ├── JwtLearningTest.java
    │               │   └── elasticsearch
    │               │       ├── DataElasticsearchLearningTest.java
    │               │       ├── ElasticsearchLearningTest.java
    │               │       ├── GymElasticDto.java
    │               │       └── TestEmployeeRepository.java
    │               └── security
    │                   └── JwtManagerTest.java
    └── resources
        ├── docker
        │   ├── config
        │   │   └── elasticsearch.yml
        │   ├── docker-compose.yml
        │   ├── elastic
        │   │   ├── Dockerfile-es
        │   │   ├── config
        │   │   │   └── elasticsearch.yml
        │   │   ├── gyms.json
        │   │   └── gyms.txt
        │   └── mysql
        │       └── Dockerfile-mysql
        └── org
            └── springframework
                └── restdocs
                    └── templates
                        └── asciidoctor
                            ├── request-fields.snippet
                            └── response-fields.snippet
```
</details>

