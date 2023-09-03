package com.example.shopping.controller.cart;

//import com.example.shopping.domain.cart.CartItem;
//import com.example.shopping.dto.cart.CartItemDto;
//import com.example.shopping.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final Logger cart_log = LoggerFactory.getLogger(CartController.class);
//    private final CartService cartService;

    @GetMapping
    @ResponseBody
    public String test() {
        return "Hello world";
    }
//    @GetMapping
//    public String showByConsumerId(HttpSession session, Model model) {
//        long sessionConsumerId = 2L;//hard coding.
//        List<CartItem> foundCartItems = cartService.showByConsumerId(sessionConsumerId);
//
//        //세션에서 excludedSet 가져오기
//        Set<Long> excludedSet = (HashSet<Long>)session.getAttribute("excludedSet");
//
//        List<CartItemDto> foundItemDtos = cartService.mapToDto(foundCartItems, excludedSet);
//        model.addAttribute("foundItemDtos", foundItemDtos);
//        return "cart";
//    }
}
