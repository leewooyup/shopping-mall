package com.example.shopping.dto.user;

import com.example.shopping.domain.user.Consumer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class LoginResponse {
    private Consumer loginUser;
    private String grade;
    private Double discountRate;
}
