package com.example.shopping.domain.cart;

import lombok.Getter;

@Getter
public class CartPager {
    private CartPageVo paging;
    private Integer size;
    private Integer beginPage;
    private Integer endPage;
    private Boolean prev;
    private Boolean next;
    private static final int BUTTON_NO = 5;

    public void setUpPaging(CartPageVo paging) {
        this.paging = paging;
    }
    private void calcDataOfPage() {
        endPage = (int)Math.ceil(paging.getPageNo()/(double)BUTTON_NO) * BUTTON_NO;
        beginPage = (endPage-BUTTON_NO) + 1;
        prev = (beginPage == 1) ? false : true;
        next = size <= (endPage * paging.getAmount()) ? false : true;

        if(!next) {
            endPage = (int)Math.ceil(size/(double)paging.getAmount());
        }
    }

    public void setUpCartItemSize(int size) {
        this.size = size;
        calcDataOfPage();
    }
}
