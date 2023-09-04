package com.example.shopping.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class Consumer {
    private Long consumerId;
    private String userEmail;
    private String password;
    private String phoneNumber;
    private String address;
    private String userName;
    private Integer isAdmin;
}
