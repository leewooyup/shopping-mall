package com.example.shopping.dto.cart;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartItemDto {
    private Long itemId;
    private String itemName;
    private Long itemPrice;
    private Long itemQuantity;
    private String itemImgPaths;
    private Long subTotalPrice;
    private Long cartId;
    private Boolean isExcluded;

//    public CartItemDto() {
//        this.isExcluded = false;
//    }
}
