package com.example.shopping.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PutInCartDto {
    private Long itemId;
    private Long itemQuantity;
}
