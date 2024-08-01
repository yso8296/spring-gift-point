package gift.controller.user;

import gift.common.annotation.LoginUser;
import gift.common.auth.LoginInfo;
import gift.common.dto.PageResponse;
import gift.controller.user.dto.UserRequest;
import gift.controller.user.dto.UserResponse;
import gift.controller.user.dto.UserResponse.Info;
import gift.controller.user.dto.UserResponse.Point;
import gift.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/members")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<PageResponse<UserResponse.Info>> getAllUser(
        @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PageResponse<UserResponse.Info> response = userService.getAllUser(pageable);
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/{memberId}/point")
    public ResponseEntity<UserResponse.Point> addPoint(
        @PathVariable("memberId") Long memberId,
        @Valid @RequestBody UserRequest.Point request
    ) {
        UserResponse.Point response = userService.addPoint(memberId, request);
        return ResponseEntity.ok().body(response);
    }
}
