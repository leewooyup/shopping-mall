package com.example.shopping.domain.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CartPageVo {
    // 한 page 당 데이터 수.
    private int amount = 3;

    private Integer pageNo;

    private int itemStartNo;

    private Long consumerId;

    public CartPageVo(Integer pageNo, Long consumerId) {
        this.pageNo = pageNo;
        this.itemStartNo = this.amount * (pageNo-1);
        this.consumerId = consumerId;
    }
}
