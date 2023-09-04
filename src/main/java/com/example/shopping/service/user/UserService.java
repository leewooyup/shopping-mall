package com.example.shopping.service.user;

import com.example.shopping.dao.user.ConsumerDao;
import com.example.shopping.domain.user.Consumer;
import com.example.shopping.dto.user.LoginRequest;
import com.example.shopping.dto.user.LoginResponse;
import com.example.shopping.exception.MessageException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final ConsumerDao consumerDao;
    public LoginResponse login(LoginRequest loginRequest) {
        Consumer consumer = consumerDao.selectByEmail(loginRequest.getUserEmail());
        if(consumer == null) {
            throw new MessageException("존재하지 않는 이메일입니다.");
        }
        return new LoginResponse(consumer, "실버", 0.01);
    }
}
