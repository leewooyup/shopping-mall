package com.example.shopping.controller.user;

import com.example.shopping.dto.user.LoginRequest;
import com.example.shopping.dto.user.LoginResponse;
import com.example.shopping.exception.MessageException;
import com.example.shopping.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/sign")
    private String showSignPage() {
        return "login";
    }

    @PostMapping("/sign-in")
    private String signIn(LoginRequest loginRequest, Model model, HttpSession session) {
        try {
            LoginResponse loginResponse = userService.login(loginRequest);
            session.setAttribute("login_user", loginResponse.getLoginUser());
            return "redirect:/";
        } catch (MessageException e) {
            model.addAttribute("errMsg", e.getMessage());
            return "login";
        }
    }
}
