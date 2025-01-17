package swm.s3.coclimb.api.docs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;
import swm.s3.coclimb.api.RestDocsTestSupport;
import swm.s3.coclimb.api.adapter.out.aws.AwsS3Manager;
import swm.s3.coclimb.domain.gymlike.GymLike;
import swm.s3.coclimb.domain.media.Media;
import swm.s3.coclimb.domain.user.InstagramUserInfo;
import swm.s3.coclimb.domain.user.User;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerDocsTest extends RestDocsTestSupport {
    @MockBean
    AwsS3Manager awsS3Manager;

    @Test
    @DisplayName("엑세스 토큰으로 현재 로그인 유저의 정보를 조회하는 API")
    void getUserByToken() throws Exception {
        // given
        Long userId = userJpaRepository.save(User.builder()
                .name("유저")
                .instagramUserInfo(InstagramUserInfo.builder().name("instagramUsername").build())
                .build()).getId();
        String accessToken = jwtManager.issueToken(userId.toString());

        // when, then
        ResultActions result = mockMvc.perform(get("/users/me")
                        .header("Authorization", accessToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("유저"));

        // docs
        result.andDo(document("user-myinfo",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                        fieldWithPath("username").type(JsonFieldType.STRING)
                                .description("사용자이름"),
                        fieldWithPath("instagramUsername").type(JsonFieldType.STRING)
                                .description("인스타그램 사용자이름")
                )));

    }

    @Test
    @DisplayName("회원 탈퇴하는 API")
    void deleteUser() throws Exception {
        // given
        willDoNothing().given(awsS3Manager).deleteFile(any());
        userJpaRepository.save(User.builder().build());
        User user = userJpaRepository.findAll().get(0);
        IntStream.range(0, 5).forEach(i -> {
            mediaJpaRepository.save(Media.builder().user(user).build());
            gymLikeJpaRepository.save(GymLike.builder().user(user).build());
        });

        // when
        ResultActions result = mockMvc.perform(delete("/users/me")
                        .header("Authorization", jwtManager.issueToken(user.getId().toString())));
        List<Media> sut1 = mediaJpaRepository.findAll();
        List<GymLike> sut2 = gymLikeJpaRepository.findAll();
        User sut3 = userJpaRepository.findById(user.getId()).orElse(null);

        // then
        assertThat(sut1.size()).isEqualTo(0);
        assertThat(sut2.size()).isEqualTo(0);
        assertThat(sut3).isNull();
        result.andExpect(status().isNoContent());

        // docs
        result.andDo(document("user-delete",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                        headerWithName("Authorization").description("JWT 인증 토큰")
                )));
    }

}