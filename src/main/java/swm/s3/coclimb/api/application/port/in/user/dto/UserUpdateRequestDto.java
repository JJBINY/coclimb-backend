package swm.s3.coclimb.api.application.port.in.user.dto;

import lombok.Builder;
import lombok.Getter;
import swm.s3.coclimb.domain.user.User;

@Getter
@Builder
public class UserUpdateRequestDto {
    User user;
    String username;
}
