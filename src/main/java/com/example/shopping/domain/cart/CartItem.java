package com.example.shopping.domain.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class CartItem {
    private Long itemId;
    private Long itemQuantity;
    private Long cartId;
    private Long consumerId;

    public void increaseitemQuantity(Long itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
}
