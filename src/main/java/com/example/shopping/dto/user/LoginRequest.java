package com.example.shopping.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class LoginRequest {
    private String userEmail;
    private String password;
}
