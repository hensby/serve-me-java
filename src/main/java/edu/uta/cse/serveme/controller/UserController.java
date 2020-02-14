package edu.uta.cse.serveme.controller;

import edu.uta.cse.serveme.base.BaseResponse;
import edu.uta.cse.serveme.base.ResultResponse;
import edu.uta.cse.serveme.entity.User;
import edu.uta.cse.serveme.entity.UserInfo;
import edu.uta.cse.serveme.entity.UserToken;
import edu.uta.cse.serveme.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/myself")
    public BaseResponse<User> myself(Authentication auth) {
        return new ResultResponse<>(userService.userById((Long) auth.getPrincipal()));
    }

    @GetMapping(value = "/info")
    public BaseResponse<UserInfo> info(Authentication auth) {
        return new ResultResponse<>(userService.infoByUid((Long) auth.getPrincipal()));
    }

    @GetMapping(value = "/getById")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<User> getById(@RequestParam Integer uid) {
        return null;
    }

    @PostMapping(value = "/token")
    public BaseResponse<Long> token(@RequestBody UserToken userToken, Authentication auth) {
        userToken.setUid((Long) auth.getPrincipal());
        return new ResultResponse<>(userService.updateToken(userToken));
    }
}
