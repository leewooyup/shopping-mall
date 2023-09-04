package com.example.shopping.controller.cart;

//import com.example.shopping.domain.cart.CartItem;
//import com.example.shopping.dto.cart.CartItemDto;
//import com.example.shopping.service.cart.CartService;

import com.example.shopping.domain.cart.CartItem;
import com.example.shopping.domain.cart.CartPageVo;
import com.example.shopping.domain.cart.CartPager;
import com.example.shopping.dto.cart.CartItemDto;
import com.example.shopping.dto.cart.PutInCartDto;
import com.example.shopping.service.cart.CartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final Logger cart_log = LoggerFactory.getLogger(CartController.class);
    private final CartService cartService;
    private CartPageVo pageVo;

    @GetMapping
    public String show(HttpSession session, Model model) {
        long sessionConsumerId = 2L;//hard coding.

        List<CartItem> foundCartItemAll = cartService.showByConsumerId(sessionConsumerId);
        if(foundCartItemAll.isEmpty()) {
            cart_log.info("foundCartItemAll is null");
            model.addAttribute("errMsg", "장바구니에 담긴 상품이 없습니다.");
        }

        pageVo = new CartPageVo(1, sessionConsumerId);
        List<CartItem> foundCartItems = cartService.showByConsumerIdWithPaging(pageVo);
        CartPager pager = cartService.setUpPaging(pageVo, foundCartItemAll.size());

        //세션에서 excludedSet 가져오기
        Set<Long> excludedSet = (HashSet<Long>)session.getAttribute("excludedSet");

        List<CartItemDto> foundItemDtoAll = cartService.mapToDto(foundCartItemAll, excludedSet);
        List<CartItemDto> foundItemDtos = cartService.mapToDto(foundCartItems, excludedSet);

        model.addAttribute("foundItemDtoAll", foundItemDtoAll);
        model.addAttribute("foundItemDtos", foundItemDtos);
        model.addAttribute("pager", pager);
        return "cart";
    }

    @PostMapping("/insert")
    public String registerItem(@RequestParam String jsonString) {
        long sessionConsumerId = 2L;//hard coding.
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            //JSON 문자열을 Java 객체로 변환
            PutInCartDto putInCartDto = objectMapper.readValue(jsonString, PutInCartDto.class);
            //insert logic.
            cartService.register(putInCartDto, sessionConsumerId);
            pageVo = new CartPageVo(1, sessionConsumerId);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/sm/c/api/get";
    }
}
