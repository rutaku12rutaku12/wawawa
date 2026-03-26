package web.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import web.user.dto.UserLoginDto;
import web.user.dto.UserSignupDto;
import web.user.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public void signup(@RequestBody UserSignupDto dto) {
        userService.signup(dto);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginDto dto) {
        return userService.login(dto);
    }
    @GetMapping("/me")
    public Long me(Authentication auth) {

        if (auth == null || auth.getPrincipal().equals("anonymousUser")) {
            throw new RuntimeException("로그인 필요");
        }

        Long userId = (Long) auth.getPrincipal();

        return userId;
    }
    @GetMapping("/posts")
    public String getPosts(Authentication auth) {

        Long userId = (Long) auth.getPrincipal();

        return "현재 로그인 유저: " + userId;
    }
}