package com.example.shopping.dao.user;

import com.example.shopping.domain.user.Consumer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConsumerDao {
    public Consumer selectByEmail(String userEmail);
}
