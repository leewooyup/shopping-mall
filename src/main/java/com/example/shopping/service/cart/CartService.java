package com.example.shopping.service.cart;

import com.example.shopping.dao.cart.CartDao;
import com.example.shopping.domain.cart.*;
import com.example.shopping.dto.cart.CartItemDto;
import com.example.shopping.dto.cart.PutInCartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartDao cartDao;

    /**
     * 현재 로그인한 회원의 장바구니 상품(들) 가져오기
     * @param sessionConsumerId
     * @return List<CartItem>
     */
    public List<CartItem> showByConsumerId(long sessionConsumerId) {
        return cartDao.selectListByConsumeId(sessionConsumerId);
    }

    public List<CartItem> showByConsumerIdWithPaging(CartPageVo vo) {
        return cartDao.selectListWithPaging(vo);
    }

    /**
     * 세션에서 가져온 Set이 null일 시, 새로 만들어주기
     * @param set
     * @return SET<Long>
     */
    public Set<Long> checkValid(Set<Long> set) {
        return set == null ? new HashSet<Long>() : set;
    }

    /**
     * carItems를 cartItemDtos로 변환해주기
     * @param cartItems
     * @param excludedSet
     * @return List<CartItemDto>
     */
    public List<CartItemDto> mapToDto(List<CartItem> cartItems, Set<Long> excludedSet) {
        excludedSet = checkValid(excludedSet);
        List<CartItemDto> dtos = new ArrayList<>();
        for(CartItem cartItem : cartItems) {
            long itemId = cartItem.getItemId();
            //hard coding start.
            Long subTotalPricePerItem = 1_000_000 * cartItem.getItemQuantity();
            boolean isExcluded = excludedSet.contains(itemId);
            CartItemDto cartItemDto = CartItemDto.builder()
                    .itemId(itemId)
                    .itemName("갤럭시노트 20")
                    .itemPrice(1_000_000L)
                    .itemQuantity(cartItem.getItemQuantity())
                    .itemImgPaths("https://picsum.photos/90")
                    .subTotalPrice(subTotalPricePerItem)
                    .cartId(cartItem.getCartId())
                    .isExcluded(isExcluded)
                    .build();
            //hard coding end.
            dtos.add(cartItemDto);
        }
        return dtos;
    }

    public void modifyItemQuantity(Long cartId, Long itemQuantity) {
        CartUpdateVo vo = CartUpdateVo.builder()
                .cartId(cartId)
                .itemQuantity(itemQuantity)
                .build();
        cartDao.updateItemQuantityByCartId(vo);
    }

    public CartItem showByCartId(long cartId) {
        CartSelectVo vo = CartSelectVo.builder()
                .cartId(cartId)
                .build();
        return (CartItem)cartDao.selectByVo(vo);
    }

    public CartPager setUpPaging(CartPageVo vo, Integer size) {
        CartPager pager = new CartPager();
        pager.setUpPaging(vo);
        pager.setUpCartItemSize(size);
        return pager;
    }

    public void removeByCartId(Long cartId) {
        cartDao.deleteByCartId(cartId);
    }

    public void register(PutInCartDto putInCartDto, Long consumerId) {
        CartInsertVo vo = CartInsertVo.builder()
                .itemId(putInCartDto.getItemId())
                .itemQuantity(putInCartDto.getItemQuantity())
                .consumerId(consumerId)
                .build();
        cartDao.insert(vo);
    }
}