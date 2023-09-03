package com.example.shopping;

import com.example.shopping.domain.cart.CartItem;
import com.example.shopping.service.cart.CartService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CartItemTests {
    @Autowired
    private CartService cartService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUpTestData() throws SQLException {
        jdbcTemplate.execute("INSERT INTO cart_item VALUES(5,9,3,2), (12,1,1,2)");
    }

    @AfterEach
    public void tearDownTestData() throws SQLException {
        jdbcTemplate.execute("DELETE FROM cart_item");
    }

    @Test
    @DisplayName("cart_id로 수량 변경")
    public void test_1() {
        long cartId = 3L;
        CartItem foundCartItem = cartService.showByCartId(cartId);
        long itemQuantity = foundCartItem.getItemQuantity();// 9L
        cartService.modifyItemQuantity(cartId, itemQuantity+2);
        CartItem updatedCartItem = cartService.showByCartId(cartId);
        assertEquals(11L, updatedCartItem.getItemQuantity());
    }
}