package com.example.shopping.domain.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CartUpdateVo {
    private Long cartId;
    private Long itemQuantity;
}