package com.example.shopping.domain.cart;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartInsertVo {
    private Long itemId;
    private Long itemQuantity;
    private Long cartId;
    private Long consumerId;
}
