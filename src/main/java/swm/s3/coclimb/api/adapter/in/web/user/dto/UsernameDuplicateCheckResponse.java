package swm.s3.coclimb.api.adapter.in.web.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsernameDuplicateCheckResponse {
    boolean duplicate;

    @Builder
    public UsernameDuplicateCheckResponse(boolean duplicate) {
        this.duplicate = duplicate;
    }
}
