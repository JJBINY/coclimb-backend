package swm.s3.coclimb.api.adapter.in.web.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import swm.s3.coclimb.api.application.port.in.user.dto.UserUpdateRequestDto;
import swm.s3.coclimb.domain.user.User;

@Getter
@NoArgsConstructor
public class UserUpdateRequest {
    String username;

    public UserUpdateRequestDto toServiceDto(User user) {
        return UserUpdateRequestDto.builder()
                .user(user)
                .username(username)
                .build();
    }

    @Builder
    public UserUpdateRequest(String username) {
        this.username = username;
    }
}
