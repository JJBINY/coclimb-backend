package swm.s3.coclimb.api.adapter.in.web.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInfoResponse {
    String username;
    String instagramUsername;

    @Builder
    public UserInfoResponse(String username, String instagramUsername) {
        this.username = username;
        this.instagramUsername = instagramUsername;
    }
}
