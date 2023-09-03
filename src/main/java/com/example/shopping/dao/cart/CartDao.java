package com.example.shopping.dao.cart;

import com.example.shopping.domain.cart.CartItem;
import com.example.shopping.domain.cart.CartSelectVo;
import com.example.shopping.domain.cart.CartUpdateVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartDao {
    List<CartItem> selectListByConsumeId(Long consumerId);
    CartItem selectByVo(CartSelectVo vo);
    void updateItemQuantityByCartId(CartUpdateVo vo);

}