package swm.s3.coclimb.api.adapter.in.web.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swm.s3.coclimb.api.adapter.in.web.user.dto.UserInfoResponse;
import swm.s3.coclimb.api.adapter.in.web.user.dto.UserUpdateRequest;
import swm.s3.coclimb.api.adapter.in.web.user.dto.UsernameDuplicateCheckResponse;
import swm.s3.coclimb.api.application.port.in.user.UserCommand;
import swm.s3.coclimb.api.application.port.in.user.UserQuery;
import swm.s3.coclimb.config.argumentresolver.LoginUser;
import swm.s3.coclimb.domain.user.User;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserQuery userQuery;
    private final UserCommand userCommand;

    @GetMapping("/users/me")
    public ResponseEntity<UserInfoResponse> getMyInfo(@LoginUser User user) {
        return ResponseEntity.ok(UserInfoResponse.builder()
                .username(user.getName())
                .instagramUsername(user.getInstagramUserInfo().getName())
                .build());
    }

    @PatchMapping("/users/me")
    public ResponseEntity<Void> updateMyInfo(@LoginUser User user, @RequestBody UserUpdateRequest request) {
        userCommand.updateUser(request.toServiceDto(user));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/users/me")
    public ResponseEntity<Void> deleteMyInfo(@LoginUser User user) {
        userCommand.deleteUser(user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/checkDuplicate")
    public ResponseEntity<UsernameDuplicateCheckResponse> checkDuplicateUsername(@RequestParam String username) {
        return ResponseEntity.ok(UsernameDuplicateCheckResponse.builder()
                .duplicate(userQuery.isDuplicateUsername(username))
                .build());
    }
}
