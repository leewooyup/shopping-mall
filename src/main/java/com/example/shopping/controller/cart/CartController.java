package com.example.shopping.controller.cart;

//import com.example.shopping.domain.cart.CartItem;
//import com.example.shopping.dto.cart.CartItemDto;
//import com.example.shopping.service.cart.CartService;

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
import org.springframework.web.bind.annotation.GetMapping;
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

//    @GetMapping
//    @ResponseBody
//    public String test() {
//        return "Hello world";
//    }
    @GetMapping
    public String show(HttpSession session, Model model) {
        long sessionConsumerId = 2L;//hard coding.

        List<CartItem> foundCartItemAll = cartService.showByConsumerId(sessionConsumerId);

        CartPageVo vo = new CartPageVo(1, sessionConsumerId);
        List<CartItem> foundCartItems = cartService.showByConsumerIdWithPaging(vo);
        CartPager pager = cartService.setUpPaging(vo, foundCartItemAll.size());

        //세션에서 excludedSet 가져오기
        Set<Long> excludedSet = (HashSet<Long>)session.getAttribute("excludedSet");
        cart_log.info("excludedSet: " + excludedSet);

        List<CartItemDto> foundItemDtoAll = cartService.mapToDto(foundCartItemAll, excludedSet);
        List<CartItemDto> foundItemDtos = cartService.mapToDto(foundCartItems, excludedSet);

        model.addAttribute("foundItemDtoAll", foundItemDtoAll);
        model.addAttribute("foundItemDtos", foundItemDtos);
        model.addAttribute("pager", pager);
        return "cart";
    }
}
