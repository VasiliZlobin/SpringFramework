package ru.vasili_zlobin.springsecurity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vasili_zlobin.springsecurity.model.User;
import ru.vasili_zlobin.springsecurity.services.AuthenticationService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class DemoController {
    private final AuthenticationService authenticationService;

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/unsecured")
    public String unsecuredPage() {
        return "unsecured";
    }

    @GetMapping("/auth_page")
    public String authPage() {
        return "authenticated";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/user_info")
    public String userInfo(Principal principal) {
        User user = authenticationService.findByUsername(principal.getName());
        return String.format("%s : %s", user.getName(), user.getPassword());
    }
}
