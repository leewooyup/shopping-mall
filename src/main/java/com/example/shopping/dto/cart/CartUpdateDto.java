package com.example.shopping.dto.cart;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CartUpdateDto {
    private Long cartId;
    private Long itemQuantity;
}
