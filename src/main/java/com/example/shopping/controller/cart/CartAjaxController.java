package com.example.shopping.controller.cart;

import com.example.shopping.domain.cart.CartItem;
import com.example.shopping.domain.cart.CartPageVo;
import com.example.shopping.domain.cart.CartPager;
import com.example.shopping.dto.cart.CartItemDto;
import com.example.shopping.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/sm/c")
@RequiredArgsConstructor
public class CartAjaxController {
    private final Logger cart_log = LoggerFactory.getLogger(CartAjaxController.class);
    private final CartService cartService;
    private CartPageVo pageVo;

    @GetMapping("/api/get")
    public String showComponent(Model model, HttpSession session) {
        cart_log.info("Cart Component here");
        long sessionConsumerId = 2L;//hard coding.

        List<CartItem> foundCartItemAll = cartService.showByConsumerId(sessionConsumerId);
        if(foundCartItemAll.isEmpty()) {
            cart_log.info("foundCartItemAll is null");
            model.addAttribute("errMsg", "장바구니에 담긴 상품이 없습니다.");
        }
        List<CartItem> foundCartItems = cartService.showByConsumerIdWithPaging(pageVo);
        Set<Long> excludedSet = (HashSet<Long>)session.getAttribute("excludedSet");

        List<CartItemDto> foundItemDtoAll = cartService.mapToDto(foundCartItemAll, excludedSet);
        List<CartItemDto> foundItemDtos = cartService.mapToDto(foundCartItems, excludedSet);


        CartPager pager = cartService.setUpPaging(pageVo, foundCartItemAll.size());
        model.addAttribute("foundItemDtoAll", foundItemDtoAll);
        model.addAttribute("foundItemDtos", foundItemDtos);
        model.addAttribute("pager", pager);
        return "cart_component";
    }
    @PostMapping("/api/v1")
    public String addUnchecked(@RequestBody Map<String, Object> requestData, HttpSession session) {
        Long sessionConsumerId = 2L;//hard coding.
        int nowPage = (Integer)requestData.get("nowPage");
        int excludedItemIdInt = (Integer)requestData.get("excludedItemId");
        long excludedItemId = Long.valueOf(excludedItemIdInt);

        //HashSet에 uncheck된 itemId 담기
        Set<Long> excludedSet = (HashSet<Long>)session.getAttribute("excludedSet");
        excludedSet = cartService.checkValid(excludedSet);
        excludedSet.add(excludedItemId);
        //세션에 업데이트된 HashSet 저장
        session.setAttribute("excludedSet", excludedSet);

        pageVo = new CartPageVo(nowPage, sessionConsumerId);
        return "redirect:/sm/c/api/get";
    }

    @PostMapping("/api/v2")
    public String removeUnchecked(@RequestBody Map<String, Object> requestData, HttpSession session) {
        Long sessionConsumerId = 2L;//hard coding.
        int nowPage = (Integer)requestData.get("nowPage");
        int includedItemIdInt = (Integer)requestData.get("includedItemId");
        long includedItemId = Long.valueOf(includedItemIdInt);
        //HashSet에 check된 itemId 빼기
        Set<Long> excludedSet = (HashSet<Long>)session.getAttribute("excludedSet");
        excludedSet.remove(includedItemId);
        //세션에 업데이트된 HashSet 저장
        session.setAttribute("excludedSet", excludedSet);
        pageVo = new CartPageVo(nowPage, sessionConsumerId);
        return "redirect:/sm/c/api/get";
    }

    @PostMapping("/api/update")
    public String updateItemQuantity(@RequestBody Map<String, String> requestData) {
        Long sessionConsumerId = 2L;//hard coding.
        int nowPage = Integer.parseInt((String)requestData.get("nowPage"));
        int cartIdInt = Integer.parseInt((String)requestData.get("cartId"));
        int itemQuantityInt = Integer.parseInt((String)requestData.get("itemQuantity"));
        long itemQuantity = Long.valueOf(itemQuantityInt);
        long cartId = Long.valueOf(cartIdInt);
        cartService.modifyItemQuantity(cartId, itemQuantity);
        pageVo = new CartPageVo(nowPage, sessionConsumerId);
        return "redirect:/sm/c/api/get";
    }

    @PostMapping("/api/page")
    public String movePage(@RequestBody Map<String, String> requestData) {
        Long sessionConsumerId = 2L;//hard coding.
        int nowPage = Integer.parseInt((String)requestData.get("nowPage"));
        pageVo = new CartPageVo(nowPage, sessionConsumerId);
        return "redirect:/sm/c/api/get";
    }

    @PostMapping("/api/delete")
    public String removeItem(@RequestBody Map<String, String> requestData) {
        Long sessionConsumerId = 2L;//hard coding.
        int cartIdInt = Integer.parseInt((String)requestData.get("cartId"));
        int nowPage = Integer.parseInt((String)requestData.get("nowPage"));
        long cartId = Long.valueOf(cartIdInt);
        cartService.removeByCartId(cartId);
        pageVo = new CartPageVo(nowPage, sessionConsumerId);
        return "redirect:/sm/c/api/get";
    }
}
